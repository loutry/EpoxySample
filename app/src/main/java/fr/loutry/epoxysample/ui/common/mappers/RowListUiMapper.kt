package fr.loutry.epoxysample.ui.common.mappers

import fr.loutry.epoxysample.domain.model.showcase.Row
import fr.loutry.epoxysample.ui.common.models.ContentItemUiModel
import fr.loutry.epoxysample.ui.common.models.RowContentsUiModel
import fr.loutry.epoxysample.ui.common.models.RowUiModel
import fr.loutry.epoxysample.ui.common.models.RowTitleUiModel
import fr.loutry.epoxysample.ui.common.models.RowGroupUiModel

object RowListUiMapper {

    operator fun invoke(domain: List<Row>): List<RowUiModel> {
        return domain.map { mapRow(it) }
    }

    private fun mapGroup(domain: Row): RowGroupUiModel {
        return RowGroupUiModel(
            id = "row " + domain.title,
            label = domain.title,
            contents = domain.contents.map { item -> mapItem(item, domain.title) }
        )
    }

    private fun mapRow(domain: Row): RowUiModel {
        return RowUiModel(
            title = mapTitle(domain),
            contents = mapContents(domain)
        )
    }

    private fun mapTitle(domain: Row): RowTitleUiModel {
        return RowTitleUiModel(
            id = "row title ${domain.title}",
            label = domain.title
        )
    }

    private fun mapContents(domain: Row): RowContentsUiModel {
        return RowContentsUiModel(
            id = " row contents ${domain.title}",
            contents = domain.contents.map { item -> mapItem(item, domain.title) }
        )
    }

    private fun mapItem(domain: Row.Item, parentTitle: String): ContentItemUiModel {
        return ContentItemUiModel(
            id = "program: ${domain.id} from $parentTitle",
            title = domain.title,
            subtitle = domain.subtitle,
            posterColor = domain.color
        )
    }
}