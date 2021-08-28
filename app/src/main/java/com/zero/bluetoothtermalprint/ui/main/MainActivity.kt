package com.zero.bluetoothtermalprint.ui.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.visible
import com.zero.bluetoothtermalprint.R
import com.zero.bluetoothtermalprint.data.db.session.Sessions
import com.zero.bluetoothtermalprint.data.model.product.DataProduct
import com.zero.bluetoothtermalprint.databinding.ActivityMainBinding
import com.zero.bluetoothtermalprint.root.App
import com.zero.bluetoothtermalprint.ui.main.bottom.FabBottomFragment
import com.zero.bluetoothtermalprint.ui.productCart.ProductCartAdapter
import com.zero.bluetoothtermalprint.utils.printer.PrinterHandler
import com.zero.bluetoothtermalprint.utils.printer.PrinterInterface
import com.zj.btsdk.BluetoothService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), PrinterInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBluetooth()

        with(binding) {
            GlobalScope.launch {
                if (!Sessions(this@MainActivity).isOpen()) {
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

                adapter.submitData(App.repoProduct.getAll())

                if (adapter.itemCount == 0){
                    llNull.visible()
                }else{
                    llNull.gone()
                }
            }

            rvData.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this@MainActivity)
            }
            fabProduct.onClick {
                FabBottomFragment().show(supportFragmentManager, "Bottom Main Activity")
            }
        }

    }

    private fun setupBluetooth() {
        val params = arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN
        )
        if (!EasyPermissions.hasPermissions(this, *params)) {
            val RC_BLUETOOTH = 0
            EasyPermissions.requestPermissions(
                this, "You need bluetooth permission",
                RC_BLUETOOTH, *params
            )
            return
        }
        mService = BluetoothService(this, PrinterHandler(this))
    }

    private val binding: ActivityMainBinding by viewBinding()
    private var mService: BluetoothService? = null
    private val adapter: ProductCartAdapter by lazy {
        ProductCartAdapter()
    }

    override fun onDeviceConnected() {
        TODO("Not yet implemented")
    }

    override fun onDeviceConnecting() {
        TODO("Not yet implemented")
    }

    override fun onDeviceConnectionLost() {
        TODO("Not yet implemented")
    }

    override fun onDeviceUnableToConnect() {
        TODO("Not yet implemented")
    }

}