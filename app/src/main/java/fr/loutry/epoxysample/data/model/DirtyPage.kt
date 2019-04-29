package fr.loutry.epoxysample.data.model

data class DirtyPage(val info: DirtyInfo, val strates: List<Strate>)

data class DirtyInfo(val title: String?)

data class Strate(
    val title: String?,
    val contents: List<StrateItem>
)

data class StrateItem(
    val contentID: String,
    val title: String?,
    val subtitle: String?,
    val URLImage: String?,
    val color: String?
)
