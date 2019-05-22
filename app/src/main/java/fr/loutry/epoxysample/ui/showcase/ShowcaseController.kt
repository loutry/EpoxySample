package fr.loutry.epoxysample.ui.showcase

import com.airbnb.epoxy.TypedEpoxyController
import fr.loutry.epoxysample.ui.common.models.RowUiModel
import fr.loutry.epoxysample.ui.common.views.epoxy.RowGroupViewModel_

class ShowcaseController : TypedEpoxyController<List<RowUiModel>>() {

    override fun buildModels(data: List<RowUiModel>) {
        data.forEach { uiModel ->
            val model = buildRow(uiModel)
            add(model)
        }
    }

    private fun buildRow(data: RowUiModel): RowGroupViewModel_ {
        return RowGroupViewModel_()
            .id(data.id)
            .title(data.label)
            .contents(data.contents)
    }
}