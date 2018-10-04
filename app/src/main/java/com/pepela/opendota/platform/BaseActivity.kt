package com.pepela.opendota.platform

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun displayErrorMessage(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

}
