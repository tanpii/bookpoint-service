package ru.tanpii.bookpoint.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Table("book_rent_queue")
data class RentEntity(
    @Id
    val rentId: UUID,
    val bookId: Long,
    val userId: UUID,
    val dueDate: LocalDate,
) {
    var deletedAt: LocalDateTime? = null

    fun softDelete() {
        deletedAt = LocalDateTime.now()
    }
}
