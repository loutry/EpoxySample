package fr.loutry.epoxysample.data.mapper

import android.graphics.Color
import fr.loutry.epoxysample.data.model.DirtyInfo
import fr.loutry.epoxysample.data.model.DirtyPage
import fr.loutry.epoxysample.data.model.Strate
import fr.loutry.epoxysample.data.model.StrateItem
import fr.loutry.epoxysample.domain.model.showcase.Info
import fr.loutry.epoxysample.domain.model.showcase.Row
import fr.loutry.epoxysample.domain.model.showcase.ShowcasePage

internal object PageMapper {

    operator fun invoke(dirtyPage: DirtyPage): ShowcasePage {
        return dirtyPage.toDomain()
    }

    private fun DirtyPage.toDomain(): ShowcasePage {
        return ShowcasePage(
            info = info.toDomain(),
            levels = strates.map { it.toDomain() }
        )
    }

    private fun DirtyInfo.toDomain(): Info {
        return Info(title = title ?: "")
    }

    private fun Strate.toDomain(): Row {
        return Row(
            title = title ?: "",
            contents = contents.map { it.toDomain() }
        )
    }

    private fun StrateItem.toDomain(): Row.Item {
        return Row.Item(
            id = contentID,
            title = title ?: "",
            subtitle = subtitle ?: "",
            color =  Color.parseColor(color)
        )
    }
}
