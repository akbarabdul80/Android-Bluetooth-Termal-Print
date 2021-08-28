package com.zero.bluetoothtermalprint.root

import android.app.Application
import com.zero.bluetoothtermalprint.data.db.RoomDB
import com.zero.bluetoothtermalprint.data.db.repo.ProductRepo
import com.zero.bluetoothtermalprint.data.db.session.Sessions

class App : Application() {
    companion object {
        lateinit var db: RoomDB
        val repoProduct by lazy {
            ProductRepo(db.product())
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = RoomDB.getDatabase(this)
    }

}