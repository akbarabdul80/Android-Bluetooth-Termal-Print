package com.zero.bluetoothtermalprint.data.db.session

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.zero.bluetoothtermalprint.BuildConfig

@SuppressLint("CommitPrefEdits")
class Sessions(context: Context) {
    companion object {
        var PREF_NAME = BuildConfig.APPLICATION_ID + ".session"

        const val open: String = "open"

    }

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor? = null

    var context: Context? = null
    val PRIVATE_MODE: Int = 0

    init {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun putData(key: String, value: String) {
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun putData(key: String, value: Boolean) {
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    fun getString(key: String): String {
        return pref.getString(key, "").toString()
    }

    fun isOpen(): Boolean {
        return pref.getBoolean(open, false)
    }

    fun Logout() {
        editor!!.clear()
        editor!!.commit()
    }
}