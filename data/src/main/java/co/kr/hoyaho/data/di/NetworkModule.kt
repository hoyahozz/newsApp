package co.kr.hoyaho.data.di

import co.kr.hoyaho.data.remote.interactor.NetworkErrorHandlerImpl
import co.kr.hoyaho.domain.interactor.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // 과제 전용으로 제출하는 것이므로 BaseUrl 보호 작업 진행 X
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val TIME_OUT_COUNT: Long = 10

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url

            // 과제 전용으로 제출하는 것이므로 API 키 보호 작업 진행 X
            request.url(
                originalHttpUrl.newBuilder()
                    .addQueryParameter("country", "us")
                    .addQueryParameter("apiKey", "ee8423178be149ff87c417ce3e1e4867")
                    .build()
            )

            chain.proceed(request.build())
        }
    }

    @Provides
    fun provideNetworkHandler(
        retrofit: Retrofit
    ): NetworkErrorHandler {
        return NetworkErrorHandlerImpl(retrofit)
    }
}
