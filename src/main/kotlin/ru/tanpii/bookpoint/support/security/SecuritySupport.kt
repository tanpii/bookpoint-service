package ru.tanpii.bookpoint.support.security

import ru.tanpii.authpoint.user.UserDataResponse
import java.util.UUID

inline fun <reified T> userContext(body: (UserDataResponse) -> T) = body(user)

inline fun <reified T> softUserContext(body: (UserDataResponse?) -> T) = body(nullableUser)

val UserDataResponse.userId: UUID
    get() = UUID.fromString(uuid)
