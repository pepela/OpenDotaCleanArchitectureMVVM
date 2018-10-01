package com.pepela.opendota

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pepela.opendota.player.PlayerActivity
import com.pepela.opendota.player.PlayerActivity.Companion.EXTRA_ACCOUNT_ID
import org.jetbrains.anko.startActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<PlayerActivity>(EXTRA_ACCOUNT_ID to 120613892L)
        finish()
    }

}
