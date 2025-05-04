package ru.tanpii.bookpoint.domain.model.dto.notifications

import ru.tanpii.bookpoint.domain.model.type.NotificationType

data class OverdueRentNotificationEvent(
    override val email: String,
    override val eventType: NotificationType,
    val firstName: String,
    val bookName: String,
    val authorName: String,
) : Event
