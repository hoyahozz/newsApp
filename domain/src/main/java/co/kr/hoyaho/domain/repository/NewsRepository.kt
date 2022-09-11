package co.kr.hoyaho.domain.repository

import co.kr.hoyaho.domain.NetworkResult
import co.kr.hoyaho.domain.model.News

interface NewsRepository {
    suspend fun getHeadLineNews(): NetworkResult<List<News>>
    suspend fun getCategoryNews(category: String): NetworkResult<List<News>>
}
