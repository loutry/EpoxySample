package com.canal.android.mycanalpoc.di

import fr.loutry.epoxysample.di.domainUseCaseModule
import fr.loutry.epoxysample.di.pocEpoxyModule

/**
 * KoinUtils -
 *
 * All koin dependency modules should be declared here
 */
object KoinUtils {

    val koinModules = listOf(
        pocEpoxyModule,
        domainUseCaseModule
    )
}
