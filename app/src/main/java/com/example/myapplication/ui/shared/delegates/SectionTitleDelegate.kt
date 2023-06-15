package com.example.myapplication.ui.shared.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutSectionTitleBinding
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class SectionTitleDelegate :
    BaseRecyclerViewDelegate<SectionTitleDelegate.Model, SectionTitleDelegate.ViewHolder>(
        viewType = R.layout.layout_section_title,
        rule = { _, data -> data is Model }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            LayoutSectionTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, data: Model, pos: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, data, pos, payloads)
        holder.takeIf { payloads.isEmpty() }?.applyData(data)
    }

    private fun ViewHolder.applyData(data: Model) {
        binding.tvTitle.text = data.text
    }

    data class Model(
        override val listId: String,
        val text: String
    ) : ListItem

    class ViewHolder(val binding: LayoutSectionTitleBinding) :
        ContainerRecyclerViewHolder(binding.root)
}
