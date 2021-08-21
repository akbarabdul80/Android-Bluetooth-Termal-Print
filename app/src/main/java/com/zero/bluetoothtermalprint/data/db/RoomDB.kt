package com.zero.bluetoothtermalprint.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zero.bluetoothtermalprint.data.db.dao.ProdukDao
import com.zero.bluetoothtermalprint.data.model.product.DataProduct

@Database(
    entities = [
        DataProduct::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract fun product(): ProdukDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            return instance ?: synchronized(this) {
                val instances = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "zerodev.db"
                ).build()
                instance = instances
                instances
            }
        }
    }
}