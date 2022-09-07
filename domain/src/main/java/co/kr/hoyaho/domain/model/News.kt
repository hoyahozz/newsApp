package co.kr.hoyaho.domain.model

/**
 * @property title : 뉴스 제목
 * @property imgUrl : 뉴스 URL
 * @property writer : 뉴스 작성자
 * @property time : 뉴스 작성 시간
 */

data class News(
    val title: String,
    val imgUrl: String,
    val writer: String,
    val time: String
)
