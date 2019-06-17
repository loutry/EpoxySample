package fr.loutry.epoxysample.ui.common.models

data class RowGroupUiModel(
    val id: String,
    val label: String,
    val contents: List<ContentItemUiModel>
)

data class ContentItemUiModel(
    val id: String,
    val title: String?,
    val subtitle: String?,
    val posterColor: Int
)
