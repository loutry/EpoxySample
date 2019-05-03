package fr.loutry.epoxysample.ui.common.views.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import fr.loutry.epoxysample.R
import fr.loutry.epoxysample.ui.common.RowLayoutManager
import fr.loutry.epoxysample.ui.common.models.ContentItemUiModel

@ModelView(
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    fullSpan = false
)
class RowGroupView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val title: TextView
    private val recycler: EpoxyRecyclerView

    init {
        View.inflate(context, R.layout.common_row_group, this)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL

        title = findViewById(R.id.common_row_title)
        recycler = findViewById(R.id.common_row_content)
        recycler.layoutManager = ShowcaseLayoutManager(context)
    }

    @TextProp
    fun setTitle(text: CharSequence) {
        title.text = text
    }

    @ModelProp
    fun setContents(contents: List<ContentItemUiModel>) {
        val contentModels = contents.map { item ->
            ProgramViewModel_()
                .id(item.id)
                .title(item.title)
                .subtitle(item.subtitle)
                .posterColor(item.posterColor)
        }
        recycler.setModels(contentModels)
    }

    class ShowcaseLayoutManager(context: Context?) : RowLayoutManager(context) {
        override fun getBreakpointsArrayRes(): Int {
            return R.array.content_row_16x9
        }

        override fun getItemPaddingRes(): Int {
            return R.dimen.one_unit
        }
    }
}
