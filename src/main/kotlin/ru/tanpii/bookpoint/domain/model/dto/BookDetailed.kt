package ru.tanpii.bookpoint.domain.model.dto

data class BookDetailed(
    val book: Book,
    val userData: UserData?,
    val self: Boolean
)
