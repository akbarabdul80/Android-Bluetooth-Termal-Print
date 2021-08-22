package com.zero.bluetoothtermalprint.utils

import java.text.SimpleDateFormat
import java.util.*

object StringFormat {

    fun String.formatDateTime(): String {
        var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val newDate: Date? = format.parse(this)
        format = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        return format.format(newDate!!)
    }
}