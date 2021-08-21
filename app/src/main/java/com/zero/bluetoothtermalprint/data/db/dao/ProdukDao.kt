package com.zero.bluetoothtermalprint.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zero.bluetoothtermalprint.data.model.product.DataProduct

@Dao
interface ProdukDao {
    @Query("SELECT * FROM product ORDER BY name ASC")
    fun getProduct(): List<DataProduct>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(data: List<DataProduct>)
}