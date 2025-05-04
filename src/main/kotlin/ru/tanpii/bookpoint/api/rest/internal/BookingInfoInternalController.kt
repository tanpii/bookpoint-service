package ru.tanpii.bookpoint.api.rest.internal

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.tanpii.bookpoint.domain.service.BookingInfoService
import ru.tanpii.bookpoint.support.consts.AUTHPOINT
import ru.tanpii.bookpoint.support.reflection.InternalApi
import java.util.*

@RestController
@RequestMapping("/api/v1/internal/booking")
class BookingInfoInternalController(
    private val bookingInfoService: BookingInfoService,
) {

    @InternalApi(AUTHPOINT)
    @GetMapping("/{userId}")
    fun getClientBookingInfo(@PathVariable userId: UUID) = bookingInfoService.getClientCurrentBookingInfo(userId)
}
