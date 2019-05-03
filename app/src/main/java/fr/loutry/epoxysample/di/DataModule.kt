package fr.loutry.epoxysample.di

import fr.loutry.epoxysample.data.ShowcaseDataSource
import fr.loutry.epoxysample.domain.Showcase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val pocEpoxyModule = module {
    single {
        ShowcaseDataSource(resources = androidContext().resources) as Showcase
    }
}
