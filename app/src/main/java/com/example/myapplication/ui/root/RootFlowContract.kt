package com.example.myapplication.ui.root

import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.flow.FlowModel
import com.revolut.kompot.navigable.flow.FlowState
import com.revolut.kompot.navigable.flow.FlowStep
import kotlinx.parcelize.Parcelize

interface RootFlowContract {
    interface FlowModelApi : FlowModel<Step, IOData.EmptyOutput>

    @Parcelize
    class State : FlowState

    sealed class Step : FlowStep {
        @Parcelize
        object ListingScreen : Step()
    }
}
