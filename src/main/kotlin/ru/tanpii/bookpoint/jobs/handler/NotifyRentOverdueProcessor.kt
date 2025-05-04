package ru.tanpii.bookpoint.jobs.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.tanpii.bookpoint.domain.model.dto.notifications.OverdueRentNotificationEvent
import ru.tanpii.bookpoint.domain.model.type.DailyJobType
import ru.tanpii.bookpoint.domain.model.type.NotificationType
import ru.tanpii.bookpoint.domain.repository.BookRepository
import ru.tanpii.bookpoint.domain.repository.RentRepository
import ru.tanpii.bookpoint.domain.service.NotificationService
import ru.tanpii.bookpoint.infrastructure.grpc.AuthpointService
import ru.tanpii.bookpoint.support.mapper.toUserData
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

@Component
class NotifyRentOverdueProcessor(
    private val rentRepository: RentRepository,
    private val bookRepository: BookRepository,
    private val authpointService: AuthpointService,
    private val notificationService: NotificationService
) : DailyStatusProcessor {

    override val event = DailyJobType.NOTIFY_OVERDUE_RENT

    @Transactional(readOnly = true)
    override fun process() {
        val currentDate = LocalDate.now()

        val overdueRents = rentRepository.findByDueDateBeforeAndDeletedAtIsNull(currentDate)
        logger.info { overdueRents }
        overdueRents.forEach {
            val userData = authpointService.getUserById(it.userId).toUserData()
            val book = bookRepository.findByBookId(it.bookId)!!

            notificationService.sendNotification(
                OverdueRentNotificationEvent(
                    userData.email,
                    NotificationType.OVERDUE_RENT,
                    userData.firstName,
                    book.bookName,
                    book.authorName
                )
            )
        }
    }
}
