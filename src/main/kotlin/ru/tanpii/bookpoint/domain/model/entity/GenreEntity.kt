package ru.tanpii.bookpoint.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("genre")
data class GenreEntity(
    val genreName: String
) {
    @Id
    var genreId: Int? = null
}
