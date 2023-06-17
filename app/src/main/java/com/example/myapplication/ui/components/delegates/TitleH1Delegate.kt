package com.example.myapplication.ui.components.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutSectionTitleBinding
import com.revolut.decorations.frames.FrameDecoratedItem
import com.revolut.decorations.frames.delegates.FrameDecorationDelegate
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class TitleH1Delegate(
    private val frameDecoration: FrameDecorationDelegate? = null
) :
    BaseRecyclerViewDelegate<TitleH1Delegate.Model, TitleH1Delegate.ViewHolder>(
        viewType = R.layout.layout_section_title,
        rule = { _, data -> data is Model }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            LayoutSectionTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, data: Model, pos: Int, payloads: List<Any>) {
        super.onBindViewHolder(
            holder,
            data.copy(frameDecoration = frameDecoration),
            pos,
            payloads
        )
        holder.takeIf { payloads.isEmpty() }?.applyData(data)
    }

    private fun ViewHolder.applyData(data: Model) {
        binding.tvTitle.text = data.text
    }

    data class Model(
        override val listId: String,
        val text: String,
        override var frameDecoration: FrameDecorationDelegate? = null
    ) : ListItem, FrameDecoratedItem

    class ViewHolder(val binding: LayoutSectionTitleBinding) :
        ContainerRecyclerViewHolder(binding.root)
}
