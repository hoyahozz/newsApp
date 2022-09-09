package co.kr.hoyaho.presentation.ui.util

import android.content.Context
import android.content.res.Resources
import co.kr.hoyaho.domain.NetworkError
import co.kr.hoyaho.presentation.R

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun NetworkError.toErrorMessage(
    context: Context
): String {
    return when (this) {
        is NetworkError.Unknown -> {
            context.getString(R.string.error_unknown)
        }
        is NetworkError.Timeout -> {
            context.getString(R.string.error_timeout)
        }
        is NetworkError.InternalServer -> {
            context.getString(R.string.error_internal_server)
        }
        is NetworkError.BadRequest -> {
            context.getString(R.string.error_bad_request, code)
        }
    }
}
