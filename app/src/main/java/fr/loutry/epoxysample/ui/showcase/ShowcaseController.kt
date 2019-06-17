package fr.loutry.epoxysample.ui.showcase

import com.airbnb.epoxy.TypedEpoxyController
import fr.loutry.epoxysample.ui.common.models.RowGroupUiModel
import fr.loutry.epoxysample.ui.common.views.epoxy.RowGroupView

class ShowcaseController : TypedEpoxyController<List<RowGroupUiModel>>() {

    override fun buildModels(data: List<RowGroupUiModel>) {
        data.forEach { uiModel ->
            val model = buildRowAsGroup(uiModel)
            add(model)
        }
    }

    private fun buildRowAsGroup(data: RowGroupUiModel): RowGroupView {
        return RowGroupView(data)
    }
}