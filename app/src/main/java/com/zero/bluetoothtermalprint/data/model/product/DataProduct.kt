package com.zero.bluetoothtermalprint.data.model.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class DataProduct(
    @PrimaryKey(autoGenerate = true) val id_produk: Int,
    val name: String,
    val price: Int,
    val qty: Int
)
