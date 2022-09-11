package co.kr.hoyaho.presentation.ui.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemOffsetDecoration(private val verticalOffset: Int, private val horizontalOffset: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.offset(verticalOffset, horizontalOffset)
    }
}
