package com.example.myapplication.ui.shared.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutHorizontalListBinding
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem
import com.revolut.rxdiffadapter.RxDiffAdapter

class HorizontalListDelegate<T : ListItem>(
    private val delegate: BaseRecyclerViewDelegate<T, *>,
    private val klass: Class<T>
) : BaseRecyclerViewDelegate<HorizontalListDelegate.Model<T>, HorizontalListDelegate.ViewHolder>(
    viewType = R.layout.layout_horizontal_list,
    rule = { _, data ->
        data is Model<*> && data.items.requireNoNulls()
            .all { klass.isAssignableFrom(it.javaClass) }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = LayoutHorizontalListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.rvContent.adapter = RxDiffAdapter(
            async = true,
            delegates = listOf(delegate)
        )
        binding.rvContent.layoutManager = LinearLayoutManager(
            binding.rvContent.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        data: Model<T>,
        pos: Int,
        payloads: List<Any>
    ) {
        super.onBindViewHolder(holder, data, pos, payloads)
        val adapter = holder.binding.rvContent.adapter
        if (adapter is RxDiffAdapter) {
            adapter.setItems(data.items)
        }
    }

    data class Model<T>(
        override val listId: String,
        val items: List<T>
    ) : ListItem

    class ViewHolder(val binding: LayoutHorizontalListBinding) :
        ContainerRecyclerViewHolder(binding.root)
}
