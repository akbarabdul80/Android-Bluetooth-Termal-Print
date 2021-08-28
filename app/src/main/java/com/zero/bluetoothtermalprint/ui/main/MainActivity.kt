package com.zero.bluetoothtermalprint.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.bluetoothtermalprint.R
import com.zero.bluetoothtermalprint.data.db.session.Sessions
import com.zero.bluetoothtermalprint.data.model.product.DataProduct
import com.zero.bluetoothtermalprint.databinding.ActivityMainBinding
import com.zero.bluetoothtermalprint.root.App
import com.zero.bluetoothtermalprint.ui.main.bottom.FabBottomFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            if (!Sessions(this@MainActivity).isOpen()) {
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

                    Sessions(this@MainActivity).putData(Sessions.open, true)
                }
            }

            fabProduct.onClick {
                FabBottomFragment().show(supportFragmentManager, "Bottom Main Activity")
            }
        }

    }

    private val binding: ActivityMainBinding by viewBinding()
}