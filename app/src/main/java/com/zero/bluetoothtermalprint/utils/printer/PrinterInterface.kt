package com.zero.bluetoothtermalprint.utils.printer

interface PrinterInterface {
    interface Connection{
        fun onDeviceConnected()

        fun onDeviceConnecting()

        fun onDeviceConnectionLost()

        fun onDeviceUnableToConnect()
    }
}