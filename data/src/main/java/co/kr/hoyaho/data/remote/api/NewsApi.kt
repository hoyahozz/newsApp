package co.kr.hoyaho.data.remote.api

import co.kr.hoyaho.data.remote.entity.NewsResponseHeader
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines")
    suspend fun getHeadLineNews(): NewsResponseHeader
}
