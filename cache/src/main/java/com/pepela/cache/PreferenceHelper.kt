package com.pepela.cache

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

open class PreferenceHelper(context: Context) {

    companion object {
        private const val PREFERENCE_NAME = "com.pepela.opendota.preferences"

        private const val PREFERENCE_LAST_CACHE_TIME = "last_cache_time"
    }


    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = prefs.getLong(PREFERENCE_LAST_CACHE_TIME, 0)
        set(value) = prefs.edit().putLong(PREFERENCE_LAST_CACHE_TIME, value).apply()

}
