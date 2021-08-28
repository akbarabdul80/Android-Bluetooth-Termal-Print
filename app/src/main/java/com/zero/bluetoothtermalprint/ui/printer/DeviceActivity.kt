package com.zero.bluetoothtermalprint.ui.printer

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.zero.bluetoothtermalprint.R
import com.zero.bluetoothtermalprint.databinding.ActivityDeviceBinding
import com.zj.btsdk.BluetoothService

class DeviceActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DEVICE_ADDRESS: String = "device_address"
    }

    private val EXTRA_DEVICE_ADDRESS = "device_address"
    private var mService: BluetoothService? = null
    private var newDeviceAdapter: ArrayAdapter<String>? = null

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothDevice.ACTION_FOUND == action) {
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                if (device!!.bondState != BluetoothDevice.BOND_BONDED) {
                    newDeviceAdapter!!.add(
                        """
                            ${device.name}
                            ${device.address}
                            """.trimIndent()
                    )
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                    title = "Pilih Perangkat"
                    if (newDeviceAdapter!!.count == 0) {
                        newDeviceAdapter!!.add("Perangkat tidak ditemukan")
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        title = "Perangkat Bluetooth"

        val pairedDeviceAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.device_name)
        binding.pairedDevices.adapter = pairedDeviceAdapter
        binding.pairedDevices.onItemClickListener = mDeviceClickListener

        newDeviceAdapter = ArrayAdapter<String>(this, R.layout.device_name)
        binding.newDevices.adapter = newDeviceAdapter
        binding.newDevices.onItemClickListener = mDeviceClickListener

        var intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(mReceiver, intentFilter)

        intentFilter = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        registerReceiver(mReceiver, intentFilter)

        mService = BluetoothService(this, null)

        val pairedDevice = mService!!.pairedDev

        if (pairedDevice.size > 0) {
            binding.titlePairedDevices.setVisibility(View.VISIBLE)
            for (device in pairedDevice) {
                pairedDeviceAdapter.add(
                    """
                        ${device.name}
                        ${device.address}
                        """.trimIndent()
                )
            }
        } else {
            val noDevice = "Tidak ada perangkat terhubung!"
            pairedDeviceAdapter.add(noDevice)
        }

        binding.buttonScan.setOnClickListener {
            scan(it)
        }
    }

    fun scan(view: View) {
        doDiscovery()
        view.visibility = View.GONE
    }

    private val mDeviceClickListener =
        OnItemClickListener { _, view, _, _ ->
            mService!!.cancelDiscovery()
            val info = (view as TextView).text.toString()
            val address = info.substring(info.length - 17)
            val intent = Intent()
            intent.putExtra(EXTRA_DEVICE_ADDRESS, address)
            setResult(RESULT_OK, intent)
            finish()
        }

    private fun doDiscovery() {
        title = "Mencari perangkat..."
        binding.titleNewDevices.visibility = View.VISIBLE
        if (mService!!.isDiscovering) {
            mService!!.cancelDiscovery()
        }
        mService!!.startDiscovery()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mService != null) {
            mService!!.cancelDiscovery()
        }
        mService = null
        unregisterReceiver(mReceiver)
    }

    private val binding: ActivityDeviceBinding by viewBinding()
}