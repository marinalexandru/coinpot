package com.example.myapplication.ui.listing

import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.BaseScreenModel
import com.revolut.kompot.navigable.screen.StateMapper
import javax.inject.Inject

internal class ListingScreenModel @Inject constructor(
    stateMapper: StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState>,
    inputData: ListingScreenContract.InputData
) : BaseScreenModel<ListingScreenContract.DomainState, ListingScreenContract.UIState, IOData.EmptyOutput>(
    stateMapper
),
    ListingScreenContract.ScreenModelApi {

    override val initialState = ListingScreenContract.DomainState(inputData.title)

}
