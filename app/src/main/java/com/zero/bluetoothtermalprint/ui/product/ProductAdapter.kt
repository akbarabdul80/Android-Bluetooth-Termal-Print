package com.zero.bluetoothtermalprint.ui.product

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.zero.bluetoothtermalprint.data.model.product.DataProduct
import com.zero.bluetoothtermalprint.databinding.ListProductBinding
import com.zero.bluetoothtermalprint.utils.StringFormat.formatRupiah

class ProductAdapter : RecyclerView.Adapter<ViewHolder<ListProductBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListProductBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListProductBinding>, position: Int) {
        with(holder.binding) {
            val item = data[position]
            tvTitle.text = item.name
            tvDec.text = item.dec
            tvPrice.text = item.price.formatRupiah()
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataProduct>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: ArrayList<DataProduct> by lazy {
        ArrayList()
    }

}