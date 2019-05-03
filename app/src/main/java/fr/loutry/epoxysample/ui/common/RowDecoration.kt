package fr.loutry.epoxysample.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import fr.loutry.epoxysample.R

class RowDecoration(context: Context, @DimenRes paddingResId: Int = DEFAULT_PADDING_RES_ID) :
    RecyclerView.ItemDecoration() {

    private val padding: Int

    init {
        padding = when (paddingResId != View.NO_ID) {
            true -> context.resources.getDimensionPixelSize(paddingResId)
            false -> context.resources.getDimensionPixelSize(DEFAULT_PADDING_RES_ID)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = 0
        outRect.right = padding
        outRect.top = 0
        outRect.bottom = 0
    }

    companion object {
        private const val DEFAULT_PADDING_RES_ID = R.dimen.one_unit
    }
}