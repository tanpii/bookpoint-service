package ru.tanpii.bookpoint.support.mapper

import org.springframework.data.domain.Page
import ru.tanpii.authpoint.user.UserDataResponse
import ru.tanpii.bookpoint.domain.model.dto.Book
import ru.tanpii.bookpoint.domain.model.dto.BookHistory
import ru.tanpii.bookpoint.domain.model.dto.BookManagement
import ru.tanpii.bookpoint.domain.model.dto.page.*
import ru.tanpii.bookpoint.domain.model.entity.AuthorEntity
import ru.tanpii.bookpoint.domain.model.entity.CommentEntity
import java.util.*

fun Page<AuthorEntity>.toPage(): AuthorPage = AuthorPage(
    total = totalElements,
    authors = content.map { it.toDto() }
)

fun List<Book>.toPage(total: Long): BookPage = BookPage(
    total = total,
    books = this
)

fun List<BookManagement>.toPage(total: Long): BookManagementPage = BookManagementPage(
    total = total,
    managementBooks = this
)

inline fun Page<CommentEntity>.toPage(userId: UUID?, getUserData: (UUID) -> UserDataResponse): CommentPage = CommentPage(
    total = totalElements,
    comments = content.map { comment -> comment.toDto(userId, getUserData.invoke(comment.userId)) }
)

fun List<BookHistory>.toPage(total: Long) = BookHistoryPage(
    total = total,
    books = this
)