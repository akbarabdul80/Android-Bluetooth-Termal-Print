package com.zero.bluetoothtermalprint.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zero.bluetoothtermalprint.data.db.dao.ProdukDao
import com.zero.bluetoothtermalprint.data.model.product.DataProduct

@Database(
    entities = [
        DataProduct::class
    ],
    version = 2
)
abstract class RoomDB : RoomDatabase() {

    abstract fun product(): ProdukDao
    // Migration from 1 to 2, Room 2.1.0

    companion object {
        @Volatile
        private var instance: RoomDB? = null
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE product ADD dec TEXT NOT NULL DEFAULT ''"
                )
            }
        }

        fun getDatabase(context: Context): RoomDB {
            return instance ?: synchronized(this) {
                val instances = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "zerodev.db"
                ).addMigrations(MIGRATION_1_2).build()
                instance = instances
                instances
            }
        }
    }
}