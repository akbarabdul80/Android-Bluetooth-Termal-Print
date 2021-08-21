package com.zero.bluetoothtermalprint.data.db.repo

import androidx.annotation.WorkerThread
import com.zero.bluetoothtermalprint.data.db.dao.ProdukDao
import com.zero.bluetoothtermalprint.data.model.product.DataProduct

class ProductRepo(private val db: ProdukDao) {
    fun getAll(): List<DataProduct> = db.getProduct()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertProduct(data: List<DataProduct>) {
        db.insertProduct(data)
    }
}