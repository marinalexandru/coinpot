package com.example.myapplication.ui.components.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutTitleOverImageBinding
import com.example.myapplication.ui.components.loadImage
import com.revolut.decorations.frames.FrameDecoratedItem
import com.revolut.decorations.frames.delegates.FrameDecorationDelegate
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class TitleAndSubtitleOverImageDelegate(
    private val onTapListener: (data: Model) -> Unit,
    private val frameDecoration: FrameDecorationDelegate? = null,
) : BaseRecyclerViewDelegate<TitleAndSubtitleOverImageDelegate.Model, TitleAndSubtitleOverImageDelegate.ViewHolder>(
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
        super.onBindViewHolder(holder, data.copy(frameDecoration = frameDecoration), pos, payloads)
        holder.applyData(data)
    }

    private fun ViewHolder.applyData(data: Model) {
        itemView.setOnClickListener { onTapListener(data) }
        binding.tvTitle.text = data.title
        binding.tvSubtitle.text = data.subtitle
        binding.ivCover.loadImage(data.imageUrl)
    }

    data class Model(
        override val listId: String,
        val title: String,
        val subtitle: String,
        val imageUrl: String,
        override var frameDecoration: FrameDecorationDelegate? = null,
    ) : ListItem, FrameDecoratedItem

    class ViewHolder(val binding: LayoutTitleOverImageBinding) :
        ContainerRecyclerViewHolder(binding.root)
}
