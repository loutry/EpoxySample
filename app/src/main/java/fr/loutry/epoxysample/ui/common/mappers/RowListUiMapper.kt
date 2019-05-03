package fr.loutry.epoxysample.ui.common.mappers

import fr.loutry.epoxysample.domain.model.showcase.Row
import fr.loutry.epoxysample.ui.common.models.ContentItemUiModel
import fr.loutry.epoxysample.ui.common.models.RowUiModel

object RowListUiMapper {

    operator fun invoke(domain: List<Row>): List<RowUiModel> {
        return domain.map { mapRow(it) }
    }

    private fun mapRow(domain: Row): RowUiModel {
        return RowUiModel(
            id = "row " + domain.title,
            label = domain.title,
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