package com.example.myapplication.ui.shared.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.LayoutImageWithTitleAndSubtitleBinding
import com.example.myapplication.ui.shared.loadImage
import com.revolut.kextensions.ContainerRecyclerViewHolder
import com.revolut.recyclerkit.delegates.BaseRecyclerViewDelegate
import com.revolut.recyclerkit.delegates.ListItem

class ImageWithTitleAndSubtitleDelegate(
    private val onTapListener: (data: Model) -> Unit,
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
        val imageUrl: String
    ) : ListItem

    class ViewHolder(val binding: LayoutImageWithTitleAndSubtitleBinding) :
        ContainerRecyclerViewHolder(binding.root)

}
