package ru.tanpii.bookpoint.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.tanpii.bookpoint.domain.model.dto.notifications.NewReservationNotificationEvent
import ru.tanpii.bookpoint.domain.model.entity.ReservationEntity
import ru.tanpii.bookpoint.domain.model.type.BookStatus
import ru.tanpii.bookpoint.domain.model.type.NotificationType
import ru.tanpii.bookpoint.domain.repository.AuthorRepository
import ru.tanpii.bookpoint.domain.repository.BookRepository
import ru.tanpii.bookpoint.domain.repository.ReservationRepository
import ru.tanpii.bookpoint.support.security.user
import ru.tanpii.bookpoint.support.security.userId
import java.time.LocalDate
import java.util.*

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
    private val notificationService: NotificationService
) {

    @Transactional
    fun reserveBook(bookId: Long) {
        if (reservationRepository.existsByUserId(user.userId)) throw RuntimeException()

        val dueDate = LocalDate.now().plusDays(3)

        val reservationEntity = ReservationEntity(
            reservationId = UUID.randomUUID(),
            bookId = bookId,
            userId = user.userId,
            dueDate = dueDate
        )

        reservationRepository.makeReservation(reservationEntity)

        bookRepository.save(
            bookRepository.findById(bookId).get().apply {
                status = BookStatus.BOOKED
            }
        ).also {
            notificationService.sendNotification(
                NewReservationNotificationEvent(
                    email = user.email,
                    eventType = NotificationType.NEW_RESERVATION,
                    firstName = user.firstName,
                    bookName = it.bookName,
                    authorName = authorRepository.findAuthorNameByAuthorId(it.authorId),
                    dueDate = dueDate
                )
            )
        }
    }

    @Transactional
    fun removeOverdueReservations() {
        val currentDate = LocalDate.now()
        val overdueBooks = reservationRepository.findBookIdByDueDateBefore(currentDate)

        reservationRepository.deleteOverdueReservations(currentDate)

        if (overdueBooks.isNotEmpty()) {
            bookRepository.updateBooksStatus(overdueBooks, BookStatus.AVAILABLE)
        }
    }

    @Transactional
    fun removeClientReservation() = reservationRepository.findByUserId(user.userId)?.let {
        bookRepository.updateBookStatus(it.bookId, BookStatus.AVAILABLE)
        reservationRepository.delete(it)
    }
}
