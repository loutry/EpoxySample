package fr.loutry.epoxysample.domain

import fr.loutry.epoxysample.domain.model.showcase.ShowcasePage
import io.reactivex.Single

interface Showcase {
    fun getPage(): Single<ShowcasePage>
}
