package ru.tanpii.bookpoint.infrastructure.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.tanpii.bookpoint.domain.model.dto.notifications.Event
import ru.tanpii.bookpoint.domain.service.NotificationService

@Service
class KafkaNotificationService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.notification.destination}")
    private val topic: String
) : NotificationService {

    override fun sendNotification(event: Event) {
        kafkaTemplate.send(topic, event.email, event)
    }
}
