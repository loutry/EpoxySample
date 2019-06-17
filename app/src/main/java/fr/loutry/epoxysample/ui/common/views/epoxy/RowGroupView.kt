package fr.loutry.epoxysample.ui.common.views.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import fr.loutry.epoxysample.R
import fr.loutry.epoxysample.ui.common.models.RowGroupUiModel

class RowGroupView(data: RowGroupUiModel) :
    EpoxyModelGroup(R.layout.common_row_group, buildModels(data)) {

    init {
        id(data.id)
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

    companion object {
        private fun buildModels(data: RowGroupUiModel): List<EpoxyModel<*>> {
            val models = ArrayList<EpoxyModel<*>>()

            models.add(RowTitleModel_()
                .id("row group title")
                .label(data.label)
            )

            val programModels = data.contents.map { item ->
                ProgramViewModel_()
                    .id(item.id)
                    .title(item.title)
                    .subtitle(item.subtitle)
                    .posterColor(item.posterColor)
            }

            models.add(
                CarouselModel_()
                    .id("row group contents")
                    .numViewsToShowOnScreen(3f)
                    .models(programModels)
            )

            return models
        }
    }
}
