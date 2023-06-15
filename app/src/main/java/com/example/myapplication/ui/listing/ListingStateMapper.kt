package com.example.myapplication.ui.listing

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.ui.shared.delegates.HorizontalListDelegate
import com.example.myapplication.ui.shared.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.shared.delegates.SectionTitleDelegate
import com.example.myapplication.ui.shared.delegates.TitleOverImageDelegate
import com.revolut.kompot.navigable.screen.StateMapper
import javax.inject.Inject

class ListingStateMapper @Inject constructor(
    private val context: Context
) : StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState> {

    override fun mapState(domainState: ListingScreenContract.DomainState): ListingScreenContract.UIState {

        val titleOverImageDelegateModels = domainState.newsList.map {
            TitleOverImageDelegate.Model(
                listId = it.sourceUrl, //TODO Replace with database ID
                text = it.title,
                imageUrl = it.cover
            )
        }

        return ListingScreenContract.UIState(
            newsSectionTitle = SectionTitleDelegate.Model(
                listId = context.getString(R.string.news_section_title),
                text = context.getString(R.string.news_section_title)
            ),
            newsHorizontalGallery = HorizontalListDelegate.Model(
                listId = titleOverImageDelegateModels.hashCode().toString(),
                items = titleOverImageDelegateModels
            ),
            cryptoSectionTitle = SectionTitleDelegate.Model(
                listId = context.getString(R.string.crypto_section_title),
                text = context.getString(R.string.crypto_section_title)
            ),
            cryptoList = domainState.newsList.map {
                ImageWithTitleAndSubtitleDelegate.Model(
                    listId = it.sourceUrl, //TODO Replace with database ID
                    imageUrl = it.cover,
                    title = it.title,
                    subtitle = it.subtitle
                )
            }
        )
    }
}
