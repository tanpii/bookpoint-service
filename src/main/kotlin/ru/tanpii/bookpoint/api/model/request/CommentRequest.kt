package ru.tanpii.bookpoint.api.model.request

data class CommentRequest(
    val comment: String,
    val rating: Int
)
