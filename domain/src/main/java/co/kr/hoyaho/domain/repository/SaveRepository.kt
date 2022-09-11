package co.kr.hoyaho.domain.repository

import co.kr.hoyaho.domain.model.News

interface SaveRepository {
    suspend fun getSavedNews(): List<News>
    suspend fun insertSavedNews(news: News)
    suspend fun getCheckSavedNews(title: String): Boolean
    suspend fun deleteSavedNews(title: String)
}
