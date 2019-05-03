package fr.loutry.epoxysample.di

import fr.loutry.epoxysample.domain.usecase.ShowcaseUseCase
import org.koin.dsl.module

val domainUseCaseModule = module {
    factory {
        ShowcaseUseCase(showcase = get())
    }
}
