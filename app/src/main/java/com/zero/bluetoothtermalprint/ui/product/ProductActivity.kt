package com.zero.bluetoothtermalprint.ui.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.bluetoothtermalprint.R
import com.zero.bluetoothtermalprint.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        with(binding) {
            btnBack.onClick { finish() }
        }
    }

    private val binding: ActivityProductBinding by viewBinding()
}