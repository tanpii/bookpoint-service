package ru.tanpii.bookpoint.support.security

import ru.tanpii.authpoint.user.UserDataResponse
import ru.tanpii.bookpoint.support.security.SecurityContextHolder.securityContext

object SecurityContextHolder {
    val securityContext = ThreadLocal<SecurityContext>()
    fun createContext(user: UserDataResponse, jwtToken: String) {
        securityContext.set(SecurityContext(user, jwtToken))
    }
}

class SecurityContext(
    var userInfo: UserDataResponse,
    var userJwt: String
)

val user: UserDataResponse
    get() = securityContext.get()?.userInfo ?: throw IllegalArgumentException("User not authenticated")

val nullableUser: UserDataResponse?
    get() = securityContext.get()?.userInfo
