package co.kr.hoyaho.data.di

import co.kr.hoyaho.data.local.dao.SaveDao
import co.kr.hoyaho.data.remote.api.NewsApi
import co.kr.hoyaho.data.repository.NewsRepositoryImpl
import co.kr.hoyaho.data.repository.SaveRepositoryImpl
import co.kr.hoyaho.domain.interactor.NetworkErrorHandler
import co.kr.hoyaho.domain.repository.NewsRepository
import co.kr.hoyaho.domain.repository.SaveRepository
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

    @Provides
    fun provideSaveRepository(
        dao: SaveDao
    ): SaveRepository {
        return SaveRepositoryImpl(dao)
    }
}
