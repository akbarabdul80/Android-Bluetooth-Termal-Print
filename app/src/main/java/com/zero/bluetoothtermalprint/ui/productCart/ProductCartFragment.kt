package com.zero.bluetoothtermalprint.ui.productCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.zero.bluetoothtermalprint.databinding.FragmentProductBinding
import com.zero.bluetoothtermalprint.root.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProductCartFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvData.also {
                it.adapter = cartAdapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }

            GlobalScope.launch {
                cartAdapter.submitData(App.repoProduct.getAll())
            }
        }


    }

    private val binding: FragmentProductBinding by viewBinding()
    private val cartAdapter: ProductCartAdapter by lazy {
        ProductCartAdapter()
    }
}