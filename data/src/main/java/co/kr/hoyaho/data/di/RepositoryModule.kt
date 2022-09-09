package co.kr.hoyaho.data.di

import co.kr.hoyaho.data.remote.api.NewsApi
import co.kr.hoyaho.data.repository.NewsRepositoryImpl
import co.kr.hoyaho.domain.interactor.NetworkErrorHandler
import co.kr.hoyaho.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNewsRepository(
        api: NewsApi,
        networkErrorHandler: NetworkErrorHandler
    ): NewsRepository {
        return NewsRepositoryImpl(api, networkErrorHandler)
    }
}
