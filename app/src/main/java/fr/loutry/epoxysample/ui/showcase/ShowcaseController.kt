package fr.loutry.epoxysample.ui.showcase

import android.util.Log
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import fr.loutry.epoxysample.ui.common.models.RowContentsUiModel
import fr.loutry.epoxysample.ui.common.models.RowUiModel
import fr.loutry.epoxysample.ui.common.models.RowTitleUiModel
import fr.loutry.epoxysample.ui.common.models.RowGroupUiModel
import fr.loutry.epoxysample.ui.common.views.epoxy.ProgramViewModel_
import fr.loutry.epoxysample.ui.common.views.epoxy.Row16x9ViewModel_
import fr.loutry.epoxysample.ui.common.views.epoxy.RowGroupView
import fr.loutry.epoxysample.ui.common.views.epoxy.RowTitleModel_
import java.util.Arrays

class ShowcaseController : TypedEpoxyController<List<Any>>() {

    override fun buildModels(data: List<Any>) {
        data.forEach { uiModel ->
            when (uiModel) {
                is RowGroupUiModel -> add(buildRowAsGroup(uiModel))
                is RowUiModel -> add(buildRow(uiModel))
                else -> Log.e(ShowcaseController::class.java.simpleName, "Unknown type: $uiModel.")
            }
        }
    }

    private fun buildRowAsGroup(data: RowGroupUiModel): RowGroupView {
        return RowGroupView(data)
    }

    private fun buildRow(data: RowUiModel): List<EpoxyModel<*>> {
        return Arrays.asList(
            buildRowTitle(data.title),
            buildRowContents(data.contents)
        )
    }

    private fun buildRowTitle(data: RowTitleUiModel): RowTitleModel_ {
        return RowTitleModel_()
            .id(data.id)
            .label(data.label)
    }

    private fun buildRowContents(data: RowContentsUiModel): Row16x9ViewModel_ {
        val programModels = data.contents.map { item ->
            ProgramViewModel_()
                .id(item.id)
                .title(item.title)
                .subtitle(item.subtitle)
                .posterColor(item.posterColor)
        }

        return Row16x9ViewModel_()
            .id(data.id)
            .models(programModels)
    }
}