package fr.loutry.epoxysample.di

import fr.loutry.epoxysample.ui.showcase.ShowcaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiPageModule = module {
    viewModel {
        ShowcaseViewModel(
            showcaseUseCase = get()
        )
    }
}
