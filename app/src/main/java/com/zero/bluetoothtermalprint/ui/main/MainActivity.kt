package com.zero.bluetoothtermalprint.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zero.bluetoothtermalprint.R
import com.zero.bluetoothtermalprint.data.model.product.DataProduct
import com.zero.bluetoothtermalprint.root.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            App.repoProduct.insertProduct(
                arrayListOf(
                    DataProduct(
                        0,
                        "Keyboard Gaming",
                        500000,
                        12
                    ),
                    DataProduct(
                        0,
                        "Keyboard Gaming",
                        500000,
                        12
                    )
                )
            )
        }
    }
}