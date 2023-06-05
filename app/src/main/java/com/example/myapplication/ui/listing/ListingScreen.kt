package com.example.myapplication.ui.listing

import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.di.components.RootFlowComponent
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.BaseScreen
import com.revolut.kompot.navigable.screen.ScreenStates

class ListingScreen (title: String) :
    BaseScreen<ListingScreenContract.UIState, ListingScreenContract.InputData, IOData.EmptyOutput>(
        ListingScreenContract.InputData(title)
    ) {

    override val layoutId = R.layout.screen_listing

    override val screenComponent by lazy(LazyThreadSafetyMode.NONE) {
        (flowComponent as RootFlowComponent)
            .getListingScreenComponentBuilder()
            .screen(this)
            .inputData(inputData)
            .build()
    }

    override val screenModel by lazy(LazyThreadSafetyMode.NONE) {
        screenComponent.screenModel
    }

    override fun bindScreen(uiState: ListingScreenContract.UIState, payload: ScreenStates.UIPayload?) {
        view.findViewById<TextView>(R.id.textView).text = uiState.title
    }
}
