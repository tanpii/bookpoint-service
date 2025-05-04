package ru.tanpii.bookpoint.domain.model.dto

import org.springframework.data.annotation.Reference
import ru.tanpii.bookpoint.domain.model.type.BookStatus
import java.math.BigDecimal
import java.time.LocalDate

data class BookHistory(
    val bookId: Long,
    val bookName: String,
    val authorId: Long,
    val authorName: String,
    val authorPhotoUrl: String,
    val releaseYear: Short,
    val ageLimit: Short,
    val description: String,
    val photoUrl: String,
    val rating: BigDecimal,
    val status: BookStatus = BookStatus.AVAILABLE,
    @Reference
    val genres: ArrayList<Genre>,
    val readDate: LocalDate?
)
