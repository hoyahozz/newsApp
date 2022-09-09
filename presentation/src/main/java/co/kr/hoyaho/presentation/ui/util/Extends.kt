package co.kr.hoyaho.presentation.ui.util

import android.content.res.Resources
import co.kr.hoyaho.domain.NetworkError
import co.kr.hoyaho.presentation.R

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun NetworkError.toErrorMessage(
): String {
    return when (this) {
        is NetworkError.Unknown -> {
            Resources.getSystem().getString(R.string.error_unknown)
        }
        is NetworkError.Timeout -> {
            Resources.getSystem().getString(R.string.error_timeout)
        }
        is NetworkError.InternalServer -> {
            Resources.getSystem().getString(R.string.error_internal_server)
        }
        is NetworkError.BadRequest -> {
            "Error Code : $code, Invalid request.\nPlease run again in a few minutes."
        }
    }
}
