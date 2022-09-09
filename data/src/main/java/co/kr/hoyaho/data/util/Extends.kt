package co.kr.hoyaho.data.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private object MAXIMUM {
    const val SEC = 60
    const val MIN = 60
    const val HOUR = 24
    const val DAY = 30
    const val MONTH = 12
}

private val parseFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.KOREA)

fun String.toElapsed(): String {

    val curTime = System.currentTimeMillis()
    val regTime = parseFormat.parse(this)?.time ?: Date().time
    var diffTime = (curTime - regTime) / 1000

    return when {
        diffTime < MAXIMUM.SEC -> "a moment ago"
        MAXIMUM.SEC.let { diffTime /= it; diffTime } < MAXIMUM.MIN -> "$diffTime minutes ago"
        MAXIMUM.MIN.let { diffTime /= it; diffTime } < MAXIMUM.HOUR -> "$diffTime hours ago"
        MAXIMUM.HOUR.let { diffTime /= it; diffTime } < MAXIMUM.DAY -> "$diffTime minutes ago"
        MAXIMUM.DAY.let { diffTime /= it; diffTime } < MAXIMUM.MONTH -> "$diffTime days ago"
        else -> "$diffTime months ago"
    }
}
