package ru.tanpii.bookpoint.domain.model.dto.page

import ru.tanpii.bookpoint.domain.model.dto.BookHistory

data class BookHistoryPage(
    val total: Long,
    val books: List<BookHistory>
)
