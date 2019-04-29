package fr.loutry.epoxysample.domain.model.showcase

import androidx.annotation.ColorInt

data class ShowcasePage(val info: Info, val levels: List<Row>)

data class Info(val title: String)

data class Row(
    val title: String,
    val contents: List<Item>
) {
    data class Item(
        val id: String,
        val title: String,
        val subtitle: String,
        @ColorInt val color: Int
    )

}
