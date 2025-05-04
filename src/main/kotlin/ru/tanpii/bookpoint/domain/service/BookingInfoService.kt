package ru.tanpii.bookpoint.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.tanpii.bookpoint.domain.model.dto.BookingInfo
import ru.tanpii.bookpoint.domain.repository.BookRepository
import ru.tanpii.bookpoint.domain.repository.RentRepository
import ru.tanpii.bookpoint.domain.repository.ReservationRepository
import java.util.*

@Service
class BookingInfoService(
    private val bookRepository: BookRepository,
    private val rentRepository: RentRepository,
    private val reservationRepository: ReservationRepository
) {

    @Transactional
    fun getClientCurrentBookingInfo(userId: UUID) =
        findBookInfoWithReservation(userId) ?: findBookInfoWithRent(userId)

    private fun findBookInfoWithReservation(userId: UUID) =
        reservationRepository.findByUserId(userId)?.let {
            BookingInfo(
                book = bookRepository.findByBookId(it.bookId)!!,
                dueDate = it.dueDate
            )
        }


    private fun findBookInfoWithRent(userId: UUID) =
        rentRepository.findByUserIdAndDeletedAtIsNull(userId)?.let {
            BookingInfo(
                book = bookRepository.findByBookId(it.bookId)!!,
                dueDate = it.dueDate
            )
        }
}
