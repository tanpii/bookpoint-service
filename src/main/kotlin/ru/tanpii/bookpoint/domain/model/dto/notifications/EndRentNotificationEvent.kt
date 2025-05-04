package ru.tanpii.bookpoint.domain.model.dto.notifications

import ru.tanpii.bookpoint.domain.model.type.NotificationType
import java.time.LocalDate

data class EndRentNotificationEvent(
    override val email: String,
    override val eventType: NotificationType,
    val firstName: String,
    val bookName: String,
    val authorName: String,
    val dueDate: LocalDate
) : Event
