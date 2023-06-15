package com.example.myapplication.ui.listing

import com.example.myapplication.data.models.News
import com.example.myapplication.ui.shared.delegates.HorizontalListDelegate
import com.example.myapplication.ui.shared.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.shared.delegates.SectionTitleDelegate
import com.example.myapplication.ui.shared.delegates.TitleOverImageDelegate
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.ScreenModel
import com.revolut.kompot.navigable.screen.ScreenStates
import kotlinx.parcelize.Parcelize

class ListingScreenContract {
    interface ScreenModelApi : ScreenModel<UIState, IOData.EmptyOutput>

    @Parcelize
    data class InputData(val title: String) : IOData.Input

    data class DomainState(
        val newsList: List<News>
    ) : ScreenStates.Domain

    data class UIState(
        val newsSectionTitle: SectionTitleDelegate.Model,
        val newsHorizontalGallery: HorizontalListDelegate.Model<TitleOverImageDelegate.Model>,
        val cryptoSectionTitle: SectionTitleDelegate.Model,
        val cryptoList: List<ImageWithTitleAndSubtitleDelegate.Model>
    ) : ScreenStates.UI

}
