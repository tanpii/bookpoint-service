package ru.tanpii.bookpoint.domain.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class BookManagement(
    val book: Book,
    val userData: UserData?,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val dueDate: LocalDate?
)
