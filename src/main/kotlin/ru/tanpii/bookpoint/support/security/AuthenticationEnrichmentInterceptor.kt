package ru.tanpii.bookpoint.support.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import ru.tanpii.bookpoint.infrastructure.grpc.AuthpointService
import ru.tanpii.bookpoint.support.security.SecurityContextHolder.createContext

@Component
class AuthenticationEnrichmentInterceptor(
    private val authpointService: AuthpointService
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean =
        runCatching {
            if (request.getHeader("Authorization") != null) {
                val token = request.getHeader("Authorization").replace("Bearer ", "")
                createContext(authpointService.getUserByToken(token), token)
            }
            return super.preHandle(request, response, handler)
        }.getOrElse {
            response.status = HttpStatus.UNAUTHORIZED.value()
            false
        }
}