package ru.tanpii.bookpoint.support.mapper

import ru.tanpii.authpoint.user.UserDataResponse
import ru.tanpii.bookpoint.domain.model.dto.Author
import ru.tanpii.bookpoint.domain.model.dto.Comment
import ru.tanpii.bookpoint.domain.model.dto.Genre
import ru.tanpii.bookpoint.domain.model.entity.AuthorEntity
import ru.tanpii.bookpoint.domain.model.entity.CommentEntity
import ru.tanpii.bookpoint.domain.model.entity.GenreEntity
import ru.tanpii.bookpoint.support.security.userId
import java.util.*

fun AuthorEntity.toDto(): Author = Author(
    authorId = authorId ?: throw RuntimeException(),
    authorName = authorName,
    photoUrl = authorPhotoUrl
)

fun CommentEntity.toDto(userId: UUID?, userData: UserDataResponse): Comment = Comment(
    commentId = commentId,
    userData = userData.toUserData(),
    comment = comment,
    rating = rating,
    bookId = bookId,
    self = userData.userId == userId
)

inline fun CommentEntity.toDto(userId: UUID?, getUserData: (UUID) -> UserDataResponse): Comment =
    getUserData.invoke(this.userId).toUserData().let {
        Comment (
            commentId = commentId,
            userData = it,
            comment = comment,
            rating = rating,
            bookId = bookId,
            self = it.userId == userId
        )
    }

fun GenreEntity.toDto(): Genre = Genre(
    genreId = genreId ?: throw RuntimeException(),
    genreName = genreName,
)
