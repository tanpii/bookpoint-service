package ru.tanpii.bookpoint.infrastructure.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class KafkaStatsService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.stats.destination}")
    private val topic: String
) {

    fun sendBookStats(event: BookStatsEvent) {
        kafkaTemplate.send(topic, event.userId.toString(), event)
    }

    fun sendCommentStats(event: CommentStatsEvent) {
        kafkaTemplate.send(topic, event.userId.toString(), event)
    }
}

enum class StatsEventType {
    BOOK_READ,
    COMMENT_LEFT
}

data class BookStatsEvent(
    val userId: UUID,
    val statsType: StatsEventType = StatsEventType.BOOK_READ,
    val stats: ReadStats
) {
    data class ReadStats(val daysRead: Int)
}

data class CommentStatsEvent(
    val userId: UUID,
    val statsType: StatsEventType = StatsEventType.COMMENT_LEFT,
    val stats: CommentStats
) {
    data class CommentStats(
        val rating: Int
    )
}