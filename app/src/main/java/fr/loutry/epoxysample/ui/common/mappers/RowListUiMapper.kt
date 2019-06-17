package fr.loutry.epoxysample.ui.common.mappers

import fr.loutry.epoxysample.domain.model.showcase.Row
import fr.loutry.epoxysample.ui.common.models.ContentItemUiModel
import fr.loutry.epoxysample.ui.common.models.RowGroupUiModel

object RowListUiMapper {

    operator fun invoke(domain: List<Row>): List<RowGroupUiModel> {
        return domain.map { mapGroup(it) }
    }

    private fun mapGroup(domain: Row): RowGroupUiModel {
        return RowGroupUiModel(
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