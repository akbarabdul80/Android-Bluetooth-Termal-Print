package com.zero.bluetoothtermalprint.utils.printer

interface PrinterInterface {
    fun onDeviceConnected()

    fun onDeviceConnecting()

    fun onDeviceConnectionLost()

    fun onDeviceUnableToConnect()
}