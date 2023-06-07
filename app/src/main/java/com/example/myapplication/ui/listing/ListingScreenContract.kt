package com.example.myapplication.ui.listing

import com.example.myapplication.data.models.News
import com.example.myapplication.ui.listing.delegates.NewsBoxItemDelegate
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.ScreenModel
import com.revolut.kompot.navigable.screen.ScreenStates
import kotlinx.parcelize.Parcelize

class ListingScreenContract {
    interface ScreenModelApi : ScreenModel<UIState, IOData.EmptyOutput>

    @Parcelize
    data class InputData(val title: String) : IOData.Input

    data class DomainState(
        val title: String,
        val newsList: List<News>
    ) : ScreenStates.Domain

    data class UIState(
        val title: String,
        val newsList: List<NewsBoxItemDelegate.NewsBoxItem>
    ) : ScreenStates.UI

}
