package com.example.myapplication.ui.shared.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutImageWithTitleAndSubtitleBinding
import com.example.myapplication.ui.shared.loadImage
import com.revolut.decorations.dividers.DividerDecoratedItem
import com.revolut.decorations.dividers.delegates.DividerDecorationDelegate
import com.revolut.decorations.dividers.delegates.lines.LineDividerDecorationDelegate
import com.revolut.decorations.frames.FrameDecoratedItem
import com.revolut.decorations.frames.delegates.FrameDecorationDelegate
import com.revolut.decorations.frames.delegates.PaddedFrameDecorationDelegate
import com.revolut.decorations.overlay.OverlayDecoratedItem
import com.revolut.decorations.overlay.delegates.OverlayDecorationDelegate
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class ImageWithTitleAndSubtitleDelegate(
    private val onTapListener: (data: Model) -> Unit
) : BaseRecyclerViewDelegate<ImageWithTitleAndSubtitleDelegate.Model, ImageWithTitleAndSubtitleDelegate.ViewHolder>(
    viewType = R.layout.layout_image_with_title_and_subtitle,
    rule = { _, data -> data is Model }
) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            LayoutImageWithTitleAndSubtitleBinding.inflate(
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
        binding.tvTitle.text = data.title
        binding.tvSubtitle.text = data.subtitle
        binding.ivCover.loadImage(data.imageUrl)
    }

    data class Model(
        override val listId: String,
        val title: String,
        val subtitle: String,
        val imageUrl: String,
        override var topDecoration: DividerDecorationDelegate? = null,
        override var bottomDecoration: DividerDecorationDelegate? = LineDividerDecorationDelegate(),
        override var leftDecoration: DividerDecorationDelegate? = null,
        override var rightDecoration: DividerDecorationDelegate? = null,
        override var frameDecoration: FrameDecorationDelegate? = PaddedFrameDecorationDelegate(
            topPadding = R.dimen.vertical_item_spacing,
            bottomPadding = R.dimen.vertical_item_spacing,
            leftPadding = R.dimen.horizontal_item_spacing,
            rightPadding = R.dimen.horizontal_item_spacing
        ),
        override var overlayColorDecoration: OverlayDecorationDelegate? = null
    ) : ListItem, DividerDecoratedItem, FrameDecoratedItem, OverlayDecoratedItem

    class ViewHolder(val binding: LayoutImageWithTitleAndSubtitleBinding) :
        ContainerRecyclerViewHolder(binding.root)

}
