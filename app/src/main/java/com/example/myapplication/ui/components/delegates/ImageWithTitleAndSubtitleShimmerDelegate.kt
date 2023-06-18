package com.example.myapplication.ui.components.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutImageWithTitleAndSubtitleShimmerBinding
import com.revolut.decorations.frames.FrameDecoratedItem
import com.revolut.decorations.frames.delegates.FrameDecorationDelegate
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class ImageWithTitleAndSubtitleShimmerDelegate(
    private val frameDecoration: FrameDecorationDelegate? = null,
) : BaseRecyclerViewDelegate<ImageWithTitleAndSubtitleShimmerDelegate.Model, ImageWithTitleAndSubtitleShimmerDelegate.ViewHolder>(
    viewType = R.layout.layout_image_with_title_and_subtitle_shimmer,
    rule = { _, data -> data is Model }
) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            LayoutImageWithTitleAndSubtitleShimmerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, data: Model, pos: Int, payloads: List<Any>) {
        super.onBindViewHolder(
            holder,
            data.copy(frameDecoration = frameDecoration),
            pos,
            payloads
        )
    }

    data class Model(
        override val listId: String,
        override var frameDecoration: FrameDecorationDelegate? = null
    ) : ListItem, FrameDecoratedItem

    class ViewHolder(binding: LayoutImageWithTitleAndSubtitleShimmerBinding) :
        ContainerRecyclerViewHolder(binding.root)

}
