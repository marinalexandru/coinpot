package com.example.myapplication.ui.root

import com.example.myapplication.ui.listing.ListingScreen
import com.revolut.kompot.navigable.Controller
import com.revolut.kompot.navigable.root.BaseRootFlowModel
import javax.inject.Inject

class RootFlowModel @Inject constructor() :
    BaseRootFlowModel<RootFlowContract.State, RootFlowContract.Step>(),
    RootFlowContract.FlowModelApi {

    override val initialStep = RootFlowContract.Step.ListingScreen
    override val initialState = RootFlowContract.State()

    override fun getController(step: RootFlowContract.Step): Controller = when (step) {
        RootFlowContract.Step.ListingScreen -> ListingScreen(
            title = "Hello world"
        )
    }

}
