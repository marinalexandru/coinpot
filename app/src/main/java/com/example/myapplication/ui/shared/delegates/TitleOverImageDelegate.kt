package com.example.myapplication.ui.shared.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutTitleOverImageBinding
import com.example.myapplication.ui.shared.loadImage
import com.revolut.decorations.dividers.DividerDecoratedItem
import com.revolut.decorations.dividers.delegates.DividerDecorationDelegate
import com.revolut.decorations.frames.FrameDecoratedItem
import com.revolut.decorations.frames.delegates.FrameDecorationDelegate
import com.revolut.decorations.overlay.OverlayDecoratedItem
import com.revolut.decorations.overlay.delegates.OverlayDecorationDelegate
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class TitleOverImageDelegate(
    private val onTapListener: (data: Model) -> Unit,
) : BaseRecyclerViewDelegate<TitleOverImageDelegate.Model, TitleOverImageDelegate.ViewHolder>(
    viewType = R.layout.layout_title_over_image,
    rule = { _, data -> data is Model }
) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            LayoutTitleOverImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, data: Model, pos: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, data, pos, payloads)
        holder.applyData(data)
    }

    private fun ViewHolder.applyData(data: Model) {
        itemView.setOnClickListener { onTapListener(data) }
        binding.tvTitle.text = data.text
        binding.ivCover.loadImage(data.imageUrl)
    }

    data class Model(
        override val listId: String,
        val text: String,
        val imageUrl: String,
        override var topDecoration: DividerDecorationDelegate? = null,
        override var bottomDecoration: DividerDecorationDelegate? = null,
        override var leftDecoration: DividerDecorationDelegate? = null,
        override var rightDecoration: DividerDecorationDelegate? = null,
        override var frameDecoration: FrameDecorationDelegate? = null,
        override var overlayColorDecoration: OverlayDecorationDelegate? = null
    ) : ListItem, DividerDecoratedItem, FrameDecoratedItem, OverlayDecoratedItem

    class ViewHolder(val binding: LayoutTitleOverImageBinding) :
        ContainerRecyclerViewHolder(binding.root)
}
