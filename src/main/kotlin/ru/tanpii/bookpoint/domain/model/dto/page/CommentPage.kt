package ru.tanpii.bookpoint.domain.model.dto.page

import ru.tanpii.bookpoint.domain.model.dto.Comment

data class CommentPage(
    val total: Long,
    val comments: List<Comment>
)
