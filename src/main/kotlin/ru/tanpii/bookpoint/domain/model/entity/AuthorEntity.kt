package ru.tanpii.bookpoint.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("author")
data class AuthorEntity(
    val authorName: String,
    val authorPhotoUrl: String
) {
    @Id
    var authorId: Long? = null
}
