package co.kr.hoyaho.domain.model

import java.io.Serializable

/**
 * @property title : 뉴스 제목
 * @property author : 뉴스 작성자
 * @property imgUrl : 뉴스 이미지 URL
 * @property elapsed : 뉴스 작성 후 경과 시간
 * @property content : 뉴스 본문
 */

data class News(
    val title: String,
    val author: String,
    val imgUrl: String,
    val elapsed: String,
    val content: String
) : Serializable
