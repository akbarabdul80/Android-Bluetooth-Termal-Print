package com.zero.bluetoothtermalprint.utils

import java.lang.NumberFormatException
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object StringFormat {

    //Format Rupiah
    fun Int.formatRupiah(): String{
        val locateID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(locateID)
        return format.format(this.toLong())
    }

    //Format Datetime
    fun String.formatDateTime(): String {
        var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val newDate: Date? = format.parse(this)
        format = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        return format.format(newDate!!)
    }


}