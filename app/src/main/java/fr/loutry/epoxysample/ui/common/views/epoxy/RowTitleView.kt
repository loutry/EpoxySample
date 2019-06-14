package fr.loutry.epoxysample.ui.common.views.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import fr.loutry.epoxysample.R

/**
 * Epoxy Model class to display a row title.
 */
@EpoxyModelClass(layout = R.layout.common_row_title)
abstract class RowTitleModel : EpoxyModelWithHolder<RowTitleModel.RowTitleHolder>() {
    @EpoxyAttribute
    var label: String = ""

    override fun bind(holder: RowTitleHolder) {
        holder.label.text = label
    }

    override fun unbind(holder: RowTitleHolder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.label.text = null
    }

    class RowTitleHolder : EpoxyHolder() {
        lateinit var label: TextView
        override fun bindView(itemView: View) {
            label = itemView as TextView
        }
    }
}
