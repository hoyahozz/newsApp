package co.kr.hoyaho.domain.interactor

import co.kr.hoyaho.domain.NetworkError

interface NetworkErrorHandler {
    fun getError(exception: Throwable): NetworkError
}
