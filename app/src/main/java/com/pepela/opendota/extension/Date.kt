package com.pepela.opendota.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toSimpleDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd:MM:yyyy HH:mm", Locale.getDefault())
    return simpleDateFormat.format(this)
}
