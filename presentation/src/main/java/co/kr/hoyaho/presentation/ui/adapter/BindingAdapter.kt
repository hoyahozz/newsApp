package co.kr.hoyaho.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.ui.util.dp
import com.bumptech.glide.Glide

@BindingAdapter("bind_toolbar_margin")
fun View.bindToolbarMargin(isBackVisible: Boolean) {
    if (isBackVisible) {
        val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.leftMargin = 10.dp
        this.layoutParams = layoutParams
    }
}

@BindingAdapter("bind_load_url")
fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.color.gray)
        .error(R.drawable.img_not_found)
        .into(this)
}
