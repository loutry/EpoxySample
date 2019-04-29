package fr.loutry.epoxysample.domain.usecase

import fr.loutry.epoxysample.domain.Showcase
import fr.loutry.epoxysample.domain.model.showcase.ShowcasePage
import io.reactivex.Single

class ShowcaseUseCase(val showcase: Showcase) {

    operator fun invoke(): Single<ShowcasePage> {
        return showcase.getPage()
    }
}
