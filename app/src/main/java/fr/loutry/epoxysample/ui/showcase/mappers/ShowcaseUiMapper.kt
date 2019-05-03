package fr.loutry.epoxysample.ui.showcase.mappers

import fr.loutry.epoxysample.domain.model.showcase.ShowcasePage
import fr.loutry.epoxysample.ui.common.mappers.RowListUiMapper
import fr.loutry.epoxysample.ui.showcase.models.ShowcaseUiModel

object ShowcaseUiMapper {

    operator fun invoke(domain: ShowcasePage): ShowcaseUiModel {
        return ShowcaseUiModel(
            title = domain.info.title,
            content = RowListUiMapper(domain.levels)
        )
    }
}