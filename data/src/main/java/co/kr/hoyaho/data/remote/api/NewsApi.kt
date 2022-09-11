package co.kr.hoyaho.data.remote.api

import co.kr.hoyaho.data.remote.entity.NewsResponseHeader
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getHeadLineNews(): NewsResponseHeader

    @GET("top-headlines")
    suspend fun getCategoryNews(
        @Query("category") category: String
    ): NewsResponseHeader
}
