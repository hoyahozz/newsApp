package co.kr.hoyaho.data.repository

import co.kr.hoyaho.data.remote.api.NewsApi
import co.kr.hoyaho.data.remote.entity.toNews
import co.kr.hoyaho.domain.NetworkResult
import co.kr.hoyaho.domain.interactor.NetworkErrorHandler
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val networkErrorHandler: NetworkErrorHandler
) : NewsRepository {
    override suspend fun getHeadLineNews(): NetworkResult<List<News>> {

        return try {
            val response = api.getHeadLineNews().articles.map { it.toNews() }
            NetworkResult.Success(response)
        } catch (exception: Exception) {
            val errorType = networkErrorHandler.getError(exception)
            NetworkResult.Error(errorType)
        }
    }

    override suspend fun getCategoryNews(category: String): NetworkResult<List<News>> {
        return try {
            val response = api.getCategoryNews(category).articles.map { it.toNews() }
            NetworkResult.Success(response)
        } catch (exception: Exception) {
            val errorType = networkErrorHandler.getError(exception)
            NetworkResult.Error(errorType)
        }
    }
}
