package co.kr.hoyaho.data.remote.interactor

import co.kr.hoyaho.domain.NetworkError
import co.kr.hoyaho.domain.interactor.NetworkErrorHandler
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.net.SocketTimeoutException

class NetworkErrorHandlerImpl(
    private val retrofit: Retrofit
) : NetworkErrorHandler {
    override fun getError(exception: Throwable): NetworkError {
        return when (exception) {
            is SocketTimeoutException -> NetworkError.Timeout
            is HttpException -> {
                when (exception.code()) {
                    in 500..599 -> NetworkError.InternalServer
                    in 400..499 -> {
                        val code = exception.code()
                        val message = extractErrorMessage(exception.response())
                        NetworkError.BadRequest(code, message)
                    }
                    else -> NetworkError.Unknown
                }
            }
            else -> NetworkError.Unknown
        }
    }

    private fun extractErrorMessage(response: Response<*>?): String {
        val converter = retrofit.responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java,
            arrayOfNulls(0)
        )
        val baseResponse = response?.errorBody()?.let { converter.convert(it) }
        return baseResponse?.message.orEmpty()
    }

    private data class ErrorResponse(val message: String, val documentation_url: String)
}
