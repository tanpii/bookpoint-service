package ru.tanpii.bookpoint.domain.model.dto.page

import ru.tanpii.bookpoint.domain.model.dto.BookManagement

data class BookManagementPage(
    val total: Long,
    val managementBooks: List<BookManagement>,
)
