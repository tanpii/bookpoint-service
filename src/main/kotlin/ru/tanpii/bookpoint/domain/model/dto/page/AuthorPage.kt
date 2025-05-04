package ru.tanpii.bookpoint.domain.model.dto.page

import ru.tanpii.bookpoint.domain.model.dto.Author

data class AuthorPage(
    val total: Long,
    val authors: List<Author>
)
