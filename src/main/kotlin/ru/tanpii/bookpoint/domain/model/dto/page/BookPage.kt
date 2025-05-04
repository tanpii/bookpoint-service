package ru.tanpii.bookpoint.domain.model.dto.page

import ru.tanpii.bookpoint.domain.model.dto.Book

data class BookPage(
    val total: Long,
    val books: List<Book>
)
