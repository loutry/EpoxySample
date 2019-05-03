package fr.loutry.epoxysample

import android.app.Application
import com.canal.android.mycanalpoc.di.KoinUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EpoxySampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EpoxySampleApplication)
            androidFileProperties()
            modules(KoinUtils.koinModules)
        }
    }
}