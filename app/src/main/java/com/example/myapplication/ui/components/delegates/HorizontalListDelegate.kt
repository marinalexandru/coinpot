package com.example.myapplication.ui.components.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutHorizontalListBinding
import com.revolut.decorations.frames.DelegatesFrameItemDecoration
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem
import com.revolut.rxdiffadapter.RxDiffAdapter

class HorizontalListDelegate(
    private val delegates: List<BaseRecyclerViewDelegate<out ListItem, out ContainerRecyclerViewHolder>>,
) : BaseRecyclerViewDelegate<HorizontalListDelegate.Model, HorizontalListDelegate.ViewHolder>(
    viewType = R.layout.layout_horizontal_list,
    rule = { _, data ->
        data is Model
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
            delegates = listOf(*delegates.toTypedArray())
        )
        binding.rvContent.addItemDecoration(DelegatesFrameItemDecoration())
        binding.rvContent.layoutManager = LinearLayoutManager(
            binding.rvContent.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        data: Model,
        pos: Int,
        payloads: List<Any>
    ) {
        super.onBindViewHolder(holder, data, pos, payloads)
        val adapter = holder.binding.rvContent.adapter
        if (adapter is RxDiffAdapter) {
            adapter.setItems(data.items)
        }
    }

    data class Model(
        override val listId: String,
        val items: List<ListItem>
    ) : ListItem

    class ViewHolder(val binding: LayoutHorizontalListBinding) :
        ContainerRecyclerViewHolder(binding.root)
}
