package com.pepela.opendota

import android.app.Application
import timber.log.Timber

class OpenDotaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
