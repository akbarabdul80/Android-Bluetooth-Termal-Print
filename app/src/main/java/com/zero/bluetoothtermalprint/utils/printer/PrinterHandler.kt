package com.zero.bluetoothtermalprint.utils.printer

import android.os.Handler
import android.os.Message
import android.util.Log
import com.zero.bluetoothtermalprint.BuildConfig
import com.zj.btsdk.BluetoothService

class PrinterHandler(private val interfaces: PrinterInterface) : Handler() {

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            BluetoothService.MESSAGE_STATE_CHANGE -> {
                when (msg.arg1) {
                    BluetoothService.STATE_CONNECTED -> {
                        interfaces.onDeviceConnected()
                    }
                    BluetoothService.STATE_CONNECTING -> {
                        interfaces.onDeviceConnecting()
                    }
                    BluetoothService.STATE_LISTEN, BluetoothService.STATE_NONE -> {
                        if (BuildConfig.DEBUG) Log.e("BTHandler", "Bluetooth not found!")
                    }
                }
            }
            BluetoothService.MESSAGE_CONNECTION_LOST -> {
                interfaces.onDeviceConnectionLost()
            }
            BluetoothService.MESSAGE_UNABLE_CONNECT -> {
                interfaces.onDeviceUnableToConnect()
            }
        }
    }
}