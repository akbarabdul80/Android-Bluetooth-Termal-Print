package com.zero.bluetoothtermalprint.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.bluetoothtermalprint.R
import com.zero.bluetoothtermalprint.data.model.product.DataProduct
import com.zero.bluetoothtermalprint.databinding.ActivityMainBinding
import com.zero.bluetoothtermalprint.root.App
import com.zero.bluetoothtermalprint.ui.product.ProductFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            GlobalScope.launch {
                App.repoProduct.insertProduct(
                    arrayListOf(
                        DataProduct(
                            0,
                            "Keyboard Gaming",
                            "ready stock red, yellow",
                            500000,
                            12
                        ),
                        DataProduct(
                            0,
                            "Keyboard Gaming",
                            "Kready stock red, yellow ",
                            500000,
                            12
                        )
                    )
                )
            }

            fabProduct.onClick {
                ProductFragment().show(supportFragmentManager, "Product")
            }
        }

    }

    private val binding: ActivityMainBinding by viewBinding()
}