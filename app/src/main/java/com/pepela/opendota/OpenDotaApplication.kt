package com.pepela.opendota

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.pepela.opendota.di.applicationModule
import com.pepela.opendota.di.playerModule
import com.pepela.opendota.di.searchModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class OpenDotaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(applicationModule, playerModule, searchModule))

        SoLoader.init(this, false)

        if (BuildConfig.DEBUG) {
            initTimber()
            initFlipper()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initFlipper() {
        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
        client.addPlugin(NetworkFlipperPlugin())
        client.addPlugin(SharedPreferencesFlipperPlugin(this))
        client.addPlugin(LeakCanaryFlipperPlugin())
        client.start()
    }

}
