package ru.tanpii.bookpoint.support.mapper

import ru.tanpii.bookpoint.domain.model.dto.Book
import ru.tanpii.bookpoint.domain.model.dto.BookDetailed
import ru.tanpii.bookpoint.domain.model.dto.UserData
import java.util.*

fun Book.toBookDetailed(userData: UserData?, userId: UUID?) = BookDetailed(
    book = this,
    userData = userData,
    self = userData?.userId == userId && userId != null
)
