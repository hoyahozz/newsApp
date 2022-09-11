package co.kr.hoyaho.data.remote.response

import co.kr.hoyaho.data.util.toDateTime
import co.kr.hoyaho.data.util.toElapsed
import co.kr.hoyaho.domain.model.News

data class NewsResponseHeader(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsResponse>
)

data class NewsResponse(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

fun NewsResponse.toNews(): News {
    return News(
        title = this.title ?: "Unknown Title",
        author = this.author ?: "Unknown Author",
        imgUrl = this.urlToImage ?: "",
        elapsed = this.publishedAt.toDateTime().toElapsed(),
        content = this.content ?: "Has Not Content",
        publishedAt = this.publishedAt ?: this.publishedAt.toDateTime().toString()
    )
}
