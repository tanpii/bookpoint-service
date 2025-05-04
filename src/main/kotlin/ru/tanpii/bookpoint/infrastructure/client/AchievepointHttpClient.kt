package ru.tanpii.bookpoint.infrastructure.client

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import ru.tanpii.bookpoint.support.factory.WebClientFactory
import java.util.*

@Component
class AchievepointHttpClient(
    webClientFactory: WebClientFactory
) {

    val webClient by lazy {
        webClientFactory.createWebClient("achievepoint")
    }

    fun postUserStats(event: Any) =
        webClient.post()
            .uri("/api/v1/internal/stats")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(event)
            .retrieve()
            .toBodilessEntity()
            .block()
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
