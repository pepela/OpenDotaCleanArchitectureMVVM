package com.pepela.opendota.platform

import android.os.Bundle
import com.pepela.opendota.search.SearchActivity
import org.jetbrains.anko.startActivity

class LauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<SearchActivity>()
        finish()
    }

}
