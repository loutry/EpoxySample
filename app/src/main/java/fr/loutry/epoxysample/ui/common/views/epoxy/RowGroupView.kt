package fr.loutry.epoxysample.ui.common.views.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.loutry.epoxysample.R
import fr.loutry.epoxysample.ui.common.models.ContentItemUiModel

class RowGroupView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val title: TextView
    private val recycler: RecyclerView

    init {
        View.inflate(context, R.layout.common_row_group, this)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL

        title = findViewById(R.id.common_row_title)
        recycler = findViewById(R.id.common_row_content)
    }

    fun setTitle(text: CharSequence) {
        title.text = text
    }

    fun setContents(contents: List<ContentItemUiModel>) {
        // TODO
    }
}
