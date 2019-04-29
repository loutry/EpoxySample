package fr.loutry.epoxysample.data

import android.content.res.Resources
import com.squareup.moshi.Moshi
import fr.loutry.epoxysample.data.mapper.PageMapper
import fr.loutry.epoxysample.data.model.DirtyPage
import fr.loutry.epoxysample.domain.Showcase
import fr.loutry.epoxysample.domain.model.showcase.ShowcasePage
import io.reactivex.Single

class ShowcaseDataSource(private val resources: Resources) : Showcase {

    private val moshi = Moshi.Builder().build()

    override fun getPage(): Single<ShowcasePage> {
        return Single.just("mocks/vitrine_le_bureau_des_legendes.json")
            .map { filename -> fromJson(DirtyPage::class.java, filename) }
            .map { PageMapper(it) }
    }

    private fun <T : Any> fromJson(
        clazz: Class<T>,
        jsonPath: String
    ): T {
        val inputStream = resources.assets.open(jsonPath)
        val jsonRaw = inputStream.bufferedReader().use { it.readText() }
        return moshi.adapter(clazz)
            .fromJson(jsonRaw)!!
    }
}
