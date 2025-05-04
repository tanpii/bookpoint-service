package ru.tanpii.bookpoint.support.mapper

import ru.tanpii.authpoint.user.UserDataResponse
import ru.tanpii.bookpoint.domain.model.dto.UserData
import ru.tanpii.bookpoint.support.security.userId
import java.time.Instant
import java.time.ZoneOffset

fun UserDataResponse.toUserData(): UserData = UserData(
    userId = userId,
    email = email,
    firstName = firstName,
    lastName = lastName,
    birthDate = Instant.ofEpochSecond(birthdate.seconds, birthdate.nanos.toLong())
        .atZone(ZoneOffset.UTC)
        .toLocalDate(),
    photoUrl = photoUrl
)
