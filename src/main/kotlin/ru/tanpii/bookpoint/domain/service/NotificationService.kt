package ru.tanpii.bookpoint.domain.service

import ru.tanpii.bookpoint.domain.model.dto.notifications.Event

interface NotificationService {

    fun sendNotification(event: Event)
}
