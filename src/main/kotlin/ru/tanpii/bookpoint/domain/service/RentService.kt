package ru.tanpii.bookpoint.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.tanpii.bookpoint.domain.model.dto.notifications.StartRentNotificationEvent
import ru.tanpii.bookpoint.domain.model.entity.RentEntity
import ru.tanpii.bookpoint.domain.model.type.BookStatus
import ru.tanpii.bookpoint.domain.model.type.NotificationType
import ru.tanpii.bookpoint.domain.repository.BookRepository
import ru.tanpii.bookpoint.domain.repository.RentRepository
import ru.tanpii.bookpoint.domain.repository.ReservationRepository
import ru.tanpii.bookpoint.infrastructure.client.AchievepointHttpClient
import ru.tanpii.bookpoint.infrastructure.client.BookStatsEvent
import ru.tanpii.bookpoint.infrastructure.grpc.AuthpointService
import ru.tanpii.bookpoint.support.mapper.toPage
import ru.tanpii.bookpoint.support.mapper.toUserData
import ru.tanpii.bookpoint.support.pageRequest
import java.time.LocalDate
import java.util.*

@Service
class RentService(
    private val rentRepository: RentRepository,
    private val bookRepository: BookRepository,
    private val reservationRepository: ReservationRepository,
    private val notificationService: NotificationService,
    private val authpointService: AuthpointService,
    private val achievepointClient: AchievepointHttpClient,
) {

    @Transactional
    fun acceptRent(bookId: Long) {
        val clientReservation = reservationRepository.findByBookId(bookId) ?: return

        val dueDate = LocalDate.now().plusMonths(1)

        RentEntity(
            rentId = UUID.randomUUID(),
            bookId = bookId,
            userId = clientReservation.userId,
            dueDate = dueDate,
        ).also { rentRepository.makeRent(it) }

        bookRepository.updateBookStatus(bookId, BookStatus.READING)

        val userData = authpointService.getUserById(clientReservation.userId).toUserData()
        val book = bookRepository.findByBookId(bookId)!!

        notificationService.sendNotification(
            StartRentNotificationEvent(
                email = userData.email,
                eventType = NotificationType.START_RENT,
                bookName = book.bookName,
                firstName = userData.firstName,
                authorName = book.authorName,
                dueDate = dueDate,
                currentDate = LocalDate.now(),
            )
        )

        reservationRepository.delete(clientReservation)
    }

    @Transactional
    fun endRent(bookId: Long) {
        val clientRent = rentRepository.findByBookIdAndDeletedAtIsNull(bookId) ?: return

        clientRent.apply {
            softDelete()
            rentRepository.save(this)
        }

        bookRepository.updateBookStatus(bookId, BookStatus.AVAILABLE)

        achievepointClient.postUserStats(
            BookStatsEvent(
                userId = clientRent.userId,
                stats = BookStatsEvent.ReadStats(
                    daysRead = clientRent.deletedAt!!.dayOfYear - clientRent.dueDate.minusMonths(1).dayOfYear
                )
            )
        )
    }

    @Transactional(readOnly = true)
    fun getClientRentHistory(page: Int, userId: UUID) = pageRequest(page) {
        bookRepository.findRentedBooksByUserId(userId, it.pageSize, it.offset)
            .toPage(bookRepository.countRentedBooksByUserId(userId))
    }
}
