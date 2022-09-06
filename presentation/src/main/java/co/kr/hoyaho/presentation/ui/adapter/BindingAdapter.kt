package co.kr.hoyaho.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import co.kr.hoyaho.presentation.ui.util.dp

@BindingAdapter("bind_toolbar_margin")
fun bindToolbarMargin(view: View, isBackVisible: Boolean) {
    if (isBackVisible) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.leftMargin = 10.dp
        view.layoutParams = layoutParams
    }
}
