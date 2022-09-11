package co.kr.hoyaho.data.repository

import co.kr.hoyaho.data.local.dao.SaveDao
import co.kr.hoyaho.data.local.entity.toNews
import co.kr.hoyaho.data.local.entity.toSaveEntity
import co.kr.hoyaho.data.util.toDateTime
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.domain.repository.SaveRepository

class SaveRepositoryImpl(
    private val dao: SaveDao
) : SaveRepository {
    override suspend fun getSavedNews(): List<News> {
        return dao.getSavedNews().sortedBy { it.publishedAt.toDateTime() }.reversed()
            .map { it.toNews() }
    }

    override suspend fun insertSavedNews(news: News) {
        dao.insertSavedNews(news.toSaveEntity())
    }

    override suspend fun getCheckSavedNews(title: String): Boolean {
        return dao.checkSavedNews(title) != null
    }

    override suspend fun deleteSavedNews(title: String) {
        dao.deleteArticle(title)
    }
}
