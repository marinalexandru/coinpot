package com.example.myapplication.ui.listing

import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.ScreenModel
import com.revolut.kompot.navigable.screen.ScreenStates
import kotlinx.parcelize.Parcelize

class ListingScreenContract {
    interface ScreenModelApi : ScreenModel<UIState, IOData.EmptyOutput>

    @Parcelize
    data class InputData(val title: String) : IOData.Input

    data class DomainState(
        val title: String
    ) : ScreenStates.Domain

    data class UIState(val title: String) : ScreenStates.UI

}
