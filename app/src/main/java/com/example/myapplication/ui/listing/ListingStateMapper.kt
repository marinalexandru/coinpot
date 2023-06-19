package com.example.myapplication.ui.listing

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.ui.components.delegates.HorizontalListDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.components.delegates.TitleH1Delegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageDelegate
import com.example.myapplication.ui.listing.Constants.CRYPTO_SHIMMER_LIST
import com.example.myapplication.ui.listing.Constants.NEWS_SHIMMER_LIST
import com.example.myapplication.ui.state.LoadableState
import com.revolut.kompot.navigable.screen.StateMapper
import javax.inject.Inject

class ListingStateMapper @Inject constructor(
    private val context: Context
) : StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState> {

    override fun mapState(domainState: ListingScreenContract.DomainState): ListingScreenContract.UIState {

        val newsList = domainState.news.data?.map {
            TitleAndSubtitleOverImageDelegate.Model(
                listId = it.id,
                title = it.title,
                subtitle = it.subtitle,
                imageUrl = it.cover
            )
        } ?: emptyList()

        val tokenList = domainState.tokens.data?.map {
            ImageWithTitleAndSubtitleDelegate.Model(
                listId = it.id,
                imageUrl = it.logo,
                title = it.name,
                subtitle = it.description
            )
        } ?: emptyList()

        return ListingScreenContract.UIState(
            newsTitle = TitleH1Delegate.Model(
                listId = context.getString(R.string.news_section_title),
                text = context.getString(R.string.news_section_title)
            ),
            newsList = HorizontalListDelegate.Model(
                listId = newsList.hashCode().toString(),
                items = if (domainState.news is LoadableState.Loading) {
                    NEWS_SHIMMER_LIST
                } else {
                    newsList
                }
            ),
            cryptoTitle = TitleH1Delegate.Model(
                listId = context.getString(R.string.crypto_section_title),
                text = context.getString(R.string.crypto_section_title)
            ),
            cryptoList = if (domainState.tokens is LoadableState.Loading) {
                emptyList()
            } else {
                tokenList
            },
            cryptoListShimmer = if (domainState.tokens is LoadableState.Loading) {
                CRYPTO_SHIMMER_LIST
            } else {
                emptyList()
            },
            isLoading = domainState.news is LoadableState.Loading
                    || domainState.tokens is LoadableState.Loading
        )
    }

}
