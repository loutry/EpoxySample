package fr.loutry.epoxysample.ui.common.views.epoxy

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView

class MyEpoxyRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : EpoxyRecyclerView(context, attrs, defStyleAttr) {

    fun onSaveInstanceStateOpen(): Parcelable? {
        return onSaveInstanceState()
    }

    fun onRestoreInstanceStateOpen(state: Parcelable?) {
        onRestoreInstanceState(state)
    }
}