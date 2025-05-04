package ru.tanpii.authpoint.user

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import ru.tanpii.authpoint.user.UserDataServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * ru.tanpii.authpoint.user.UserDataService.
 */
public object UserDataServiceGrpcKt {
  public const val SERVICE_NAME: String = UserDataServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val getUserDataByJwtMethod: MethodDescriptor<GetUserDataJwtRequest, UserDataResponse>
    @JvmStatic
    get() = UserDataServiceGrpc.getGetUserDataByJwtMethod()

  public val getUserDataByIdMethod: MethodDescriptor<GetUserDataIdRequest, UserDataResponse>
    @JvmStatic
    get() = UserDataServiceGrpc.getGetUserDataByIdMethod()

  /**
   * A stub for issuing RPCs to a(n) ru.tanpii.authpoint.user.UserDataService service as suspending
   * coroutines.
   */
  @StubFor(UserDataServiceGrpc::class)
  public class UserDataServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<UserDataServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): UserDataServiceCoroutineStub =
        UserDataServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun getUserDataByJwt(request: GetUserDataJwtRequest, headers: Metadata =
        Metadata()): UserDataResponse = unaryRpc(
      channel,
      UserDataServiceGrpc.getGetUserDataByJwtMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun getUserDataById(request: GetUserDataIdRequest, headers: Metadata =
        Metadata()): UserDataResponse = unaryRpc(
      channel,
      UserDataServiceGrpc.getGetUserDataByIdMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the ru.tanpii.authpoint.user.UserDataService service based on Kotlin
   * coroutines.
   */
  public abstract class UserDataServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for ru.tanpii.authpoint.user.UserDataService.getUserDataByJwt.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getUserDataByJwt(request: GetUserDataJwtRequest): UserDataResponse =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method ru.tanpii.authpoint.user.UserDataService.getUserDataByJwt is unimplemented"))

    /**
     * Returns the response to an RPC for ru.tanpii.authpoint.user.UserDataService.getUserDataById.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getUserDataById(request: GetUserDataIdRequest): UserDataResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method ru.tanpii.authpoint.user.UserDataService.getUserDataById is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = UserDataServiceGrpc.getGetUserDataByJwtMethod(),
      implementation = ::getUserDataByJwt
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = UserDataServiceGrpc.getGetUserDataByIdMethod(),
      implementation = ::getUserDataById
    )).build()
  }
}
