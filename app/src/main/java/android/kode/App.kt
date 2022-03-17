package android.kode

import android.app.Application
import android.kode.presentation.di.usersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *@author Moroz V.A. on 06.03.2022
 **/

class App: Application()  {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger(if (BuildConfig.DEBUG) org.koin.core.logger.Level.ERROR else org.koin.core.logger.Level.NONE)
            //inject Android context
            androidContext(this@App)

            modules(usersModule)
        }
    }
}
