package ru.tanpii.bookpoint.jobs.handler

import org.springframework.stereotype.Component
import ru.tanpii.bookpoint.domain.model.type.DailyJobType
import ru.tanpii.bookpoint.domain.service.ReservationService

@Component
class CancelOverdueReservationProcessor(
    private val reservationService: ReservationService,
) : DailyStatusProcessor {

    override val event = DailyJobType.CANCEL_OVERDUE_RESERVATION

    override fun process() = reservationService.removeOverdueReservations()
}
