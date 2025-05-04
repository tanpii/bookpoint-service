package ru.tanpii.bookpoint.domain.service

import org.springframework.stereotype.Service
import ru.tanpii.bookpoint.api.model.request.CommentRequest
import ru.tanpii.bookpoint.domain.model.entity.CommentEntity
import ru.tanpii.bookpoint.domain.repository.CommentRepository
import ru.tanpii.bookpoint.infrastructure.grpc.AuthpointService
import ru.tanpii.bookpoint.infrastructure.kafka.CommentStatsEvent
import ru.tanpii.bookpoint.infrastructure.kafka.KafkaStatsService
import ru.tanpii.bookpoint.support.mapper.toDto
import ru.tanpii.bookpoint.support.mapper.toPage
import ru.tanpii.bookpoint.support.pageRequest
import ru.tanpii.bookpoint.support.security.nullableUser
import ru.tanpii.bookpoint.support.security.user
import ru.tanpii.bookpoint.support.security.userId
import java.util.*

@Service
class CommentService(
    private val authpointService: AuthpointService,
    private val commentRepository: CommentRepository,
    private val statsService: KafkaStatsService,
) {
    fun getBookComments(bookId: Long, page: Int) = pageRequest(page) {
        commentRepository.findAllByBookId(it, bookId)
    }.toPage(nullableUser?.userId) { uuid ->
        getUserData(uuid)
    }

    fun getUserComments(page: Int) = pageRequest(page) {
        commentRepository.findAllByUserId(it, user.userId)
    }.toPage(user.userId) { uuid ->
        getUserData(uuid)
    }

    fun addComment(bookId: Long, request: CommentRequest) = CommentEntity(
        commentId = UUID.randomUUID(),
        userId = user.userId,
        comment = request.comment,
        rating = request.rating,
        bookId = bookId
    ).apply {
        commentRepository.save(this)
    }.toDto(user.userId) { uuid ->
        getUserData(uuid)
    }.also {
        statsService.sendCommentStats(
            CommentStatsEvent(
                userId = user.userId,
                stats = CommentStatsEvent.CommentStats(
                    rating = request.rating,
                )
            )
        )
    }

    fun deleteComment(commentId: UUID) = commentRepository.deleteById(commentId)

    private fun getUserData(uuid: UUID) = authpointService.getUserById(uuid)
}
