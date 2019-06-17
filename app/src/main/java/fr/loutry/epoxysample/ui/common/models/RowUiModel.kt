package fr.loutry.epoxysample.ui.common.models

@Deprecated(
    message = "Use RowUiModel and NOT this as it promote ViewGroup usage as a CustomView." +
        "See: https://github.com/airbnb/epoxy/wiki/Grouping-Models"
)
data class RowGroupUiModel(
    val id: String,
    val label: String,
    val contents: List<ContentItemUiModel>
)

data class RowUiModel(
    val title: RowTitleUiModel,
    val contents: RowContentsUiModel
)

data class RowTitleUiModel(
    val id: String,
    val label: String
)

data class RowContentsUiModel(
    val id: String,
    val contents: List<ContentItemUiModel>
)

data class ContentItemUiModel(
    val id: String,
    val title: String?,
    val subtitle: String?,
    val posterColor: Int
)
