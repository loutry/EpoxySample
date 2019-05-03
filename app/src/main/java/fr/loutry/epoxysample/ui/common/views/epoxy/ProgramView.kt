package fr.loutry.epoxysample.ui.common.views.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import fr.loutry.epoxysample.R

/**
 * Custom view to display a strate program.
 */
class ProgramView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private  val poster: View
    private  val title: TextView
    private  val subtitle: TextView

    init {
        View.inflate(context, R.layout.common_row_item, this)
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        poster = findViewById(R.id.common_row_item_poster)
        title = findViewById(R.id.common_row_item_title)
        subtitle = findViewById(R.id.common_row_item_subtitle)
    }

    fun setPosterColor(posterColor: Int) {
        poster.setBackgroundColor(posterColor)
    }

    fun setTitle(text: CharSequence?) {
        title.text = text
    }

    fun setSubtitle(text: CharSequence?) {
        subtitle.text = text
    }
}
