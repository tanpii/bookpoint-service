package ru.tanpii.bookpoint.infrastructure.grpc

import kotlinx.coroutines.runBlocking
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import ru.tanpii.authpoint.user.UserDataServiceGrpcKt
import ru.tanpii.authpoint.user.getUserDataIdRequest
import ru.tanpii.authpoint.user.getUserDataJwtRequest
import java.util.*

@Service
class AuthpointService {

    @GrpcClient("authpoint")
    private lateinit var authpointClient: UserDataServiceGrpcKt.UserDataServiceCoroutineStub

    fun getUserByToken(token: String) = runBlocking {
        authpointClient.getUserDataByJwt(getUserDataJwtRequest { jwt = token })
    }

    fun getUserById(uuid: UUID) = runBlocking {
        authpointClient.getUserDataById(getUserDataIdRequest { this.uuid = uuid.toString() })
    }
}