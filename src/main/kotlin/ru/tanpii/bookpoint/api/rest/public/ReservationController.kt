package ru.tanpii.bookpoint.api.rest.public

import org.springframework.web.bind.annotation.*
import ru.tanpii.bookpoint.domain.service.ReservationService
import ru.tanpii.bookpoint.support.security.userContext

@RestController
@RequestMapping("/api/v1/reservation")
class ReservationController(
    private val reservationService: ReservationService
) {
    @PostMapping("/{bookId}")
    fun reserveBook(@PathVariable bookId: Long) = userContext {
        reservationService.reserveBook(bookId)
    }

    @DeleteMapping
    fun cancelBookReservation() = userContext {
        reservationService.removeClientReservation()
    }
}
