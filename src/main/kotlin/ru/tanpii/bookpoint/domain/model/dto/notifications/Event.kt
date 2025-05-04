package ru.tanpii.bookpoint.domain.model.dto.notifications

import ru.tanpii.bookpoint.domain.model.type.NotificationType

interface Event {
    val email: String
    val eventType: NotificationType
}
