package com.example.myapplication.ui.listing

import com.example.myapplication.data.models.News
import com.example.myapplication.data.models.Token
import com.example.myapplication.ui.components.delegates.HorizontalListDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleShimmerDelegate
import com.example.myapplication.ui.components.delegates.TitleH1Delegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageDelegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageShimmerDelegate
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.ScreenModel
import com.revolut.kompot.navigable.screen.ScreenStates
import kotlinx.parcelize.Parcelize

class ListingScreenContract {
    interface ScreenModelApi : ScreenModel<UIState, IOData.EmptyOutput> {
        fun onTitleOverImageTap(model: TitleAndSubtitleOverImageDelegate.Model)
        fun onImageWithTitleAndSubtitleTap(model: ImageWithTitleAndSubtitleDelegate.Model)
    }

    @Parcelize
    data class InputData(val title: String) : IOData.Input

    data class DomainState(
        val newsList: List<News>,
        val tokenList: List<Token>,
        val newsListLoading: Boolean,
        val tokenListLoading: Boolean
    ) : ScreenStates.Domain

    data class UIState(
        val newsTitle: TitleH1Delegate.Model,
        val newsList: HorizontalListDelegate.Model,
        val cryptoTitle: TitleH1Delegate.Model,
        val cryptoList: List<ImageWithTitleAndSubtitleDelegate.Model>,
        val cryptoListShimmer: List<ImageWithTitleAndSubtitleShimmerDelegate.Model>
    ) : ScreenStates.UI

}
