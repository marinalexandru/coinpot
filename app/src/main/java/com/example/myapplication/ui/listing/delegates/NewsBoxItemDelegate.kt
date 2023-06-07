package com.example.myapplication.ui.listing.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutNewsItemBinding
import com.example.myapplication.ui.shared.loadImage
import com.revolut.decorations.dividers.DividerDecoratedItem
import com.revolut.decorations.dividers.delegates.DividerDecorationDelegate
import com.revolut.decorations.frames.FrameDecoratedItem
import com.revolut.decorations.frames.delegates.FrameDecorationDelegate
import com.revolut.decorations.overlay.OverlayDecoratedItem
import com.revolut.decorations.overlay.delegates.OverlayDecorationDelegate
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.animations.holder.AnimateChangeViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class NewsBoxItemDelegate(
    private val onTapListener: (data: NewsBoxItem) -> Unit,
) : BaseRecyclerViewDelegate<NewsBoxItemDelegate.NewsBoxItem, NewsBoxItemDelegate.ViewHolder>(
    viewType = R.layout.layout_news_item,
    rule = { _, data -> data is NewsBoxItem }
) {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, data: NewsBoxItem, pos: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, data, pos, payloads)
        payloads.filterIsInstance<Payload>()
            .forEach { payload -> holder.applyPayload(payload) }

        holder.takeIf { payloads.isEmpty() }?.applyData(data)
    }

    private fun ViewHolder.applyPayload(payload: Payload) {
        payload.imageUrl?.let { binding.ivCover.loadImage(it) }
        payload.text?.let {
            binding.tvTitle.text = it
            binding.tvTitle.startAnimation(AnimationSet(true).apply {
                addAnimation(
                    ScaleAnimation(
                        1.0f,
                        1.01f,
                        1.0f,
                        1.01f,
                        binding.tvTitle.measuredWidth / 2.0f,
                        binding.tvTitle.measuredHeight / 2.0f
                    ).apply {
                        duration = 200
                    })
                addAnimation(
                    ScaleAnimation(
                        1.01f,
                        1.0f,
                        1.01f,
                        1.0f,
                        binding.tvTitle.measuredWidth / 2.0f,
                        binding.tvTitle.measuredHeight / 2.0f
                    ).apply {
                        startOffset = 200
                        duration = 200
                    })
            })
        }
    }

    private fun ViewHolder.applyData(data: NewsBoxItem) {
        itemView.setOnClickListener { onTapListener(data) }
        binding.tvTitle.text = data.text
        binding.ivCover.loadImage(data.imageUrl)
    }

    data class Payload(
        val text: CharSequence?,
        val imageUrl: String?
    )

    data class NewsBoxItem(
        override val listId: String,
        val text: String,
        val imageUrl: String,
        override var topDecoration: DividerDecorationDelegate? = null,
        override var bottomDecoration: DividerDecorationDelegate? = null,
        override var leftDecoration: DividerDecorationDelegate? = null,
        override var rightDecoration: DividerDecorationDelegate? = null,
        override var frameDecoration: FrameDecorationDelegate? = null,
        override var overlayColorDecoration: OverlayDecorationDelegate? = null
    ) : ListItem, DividerDecoratedItem, FrameDecoratedItem, OverlayDecoratedItem {

        override fun calculatePayload(oldItem: Any): Any? {
            if (oldItem !is NewsBoxItem) return null

            return Payload(
                text = text.takeIf { text != oldItem.text },
                imageUrl = imageUrl.takeIf { imageUrl != oldItem.imageUrl }
            )
        }

    }

    class ViewHolder(val binding: LayoutNewsItemBinding) :
        ContainerRecyclerViewHolder(binding.root),
        AnimateChangeViewHolder {

        override fun canAnimateChange(payloads: List<Any>): Boolean {
            return payloads.filterIsInstance<Payload>().any { it.text != null }
        }

        override fun endChangeAnimation(holder: RecyclerView.ViewHolder) {
            binding.tvTitle.clearAnimation()
        }
    }

}
