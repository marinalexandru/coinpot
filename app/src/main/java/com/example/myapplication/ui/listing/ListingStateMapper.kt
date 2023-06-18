package com.example.myapplication.ui.listing

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.ui.components.delegates.HorizontalListDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleShimmerDelegate
import com.example.myapplication.ui.components.delegates.TitleH1Delegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageDelegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageShimmerDelegate
import com.revolut.kompot.navigable.screen.StateMapper
import javax.inject.Inject

class ListingStateMapper @Inject constructor(
    private val context: Context
) : StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState> {

    override fun mapState(domainState: ListingScreenContract.DomainState): ListingScreenContract.UIState {

        val titleOverImageDelegateModels = domainState.newsList.map {
            TitleAndSubtitleOverImageDelegate.Model(
                listId = it.id,
                title = it.title,
                subtitle = it.subtitle,
                imageUrl = it.cover
            )
        }
        val titleOverImageShimmerDelegateModels = listOf(
            TitleAndSubtitleOverImageShimmerDelegate.Model(listId = "109724"),
            TitleAndSubtitleOverImageShimmerDelegate.Model(listId = "109725")
        )


        return ListingScreenContract.UIState(
            newsTitle = TitleH1Delegate.Model(
                listId = context.getString(R.string.news_section_title),
                text = context.getString(R.string.news_section_title)
            ),
            newsList = HorizontalListDelegate.Model(
                listId = titleOverImageDelegateModels.hashCode().toString(),
                items = if (domainState.newsListLoading) titleOverImageShimmerDelegateModels else titleOverImageDelegateModels
            ),
            cryptoTitle = TitleH1Delegate.Model(
                listId = context.getString(R.string.crypto_section_title),
                text = context.getString(R.string.crypto_section_title)
            ),
            cryptoList = if (domainState.tokenListLoading) emptyList() else domainState.tokenList.map {
                ImageWithTitleAndSubtitleDelegate.Model(
                    listId = it.id,
                    imageUrl = it.logo,
                    title = it.name,
                    subtitle = it.description
                )
            },
            cryptoListShimmer = if (domainState.newsListLoading) {
                listOf(
                    ImageWithTitleAndSubtitleShimmerDelegate.Model("109723"),
                )
            } else {
                emptyList()
            }
        )
    }
}
