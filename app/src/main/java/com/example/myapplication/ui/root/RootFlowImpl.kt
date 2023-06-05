package com.example.myapplication.ui.root

import com.example.myapplication.R
import com.example.myapplication.di.components.RootFlowComponent
import com.example.myapplication.ui.App
import com.revolut.kompot.common.IOData
import com.revolut.kompot.dialog.DefaultLoadingDialogDisplayer
import com.revolut.kompot.dialog.DialogDisplayer
import com.revolut.kompot.navigable.root.RootFlow
import com.revolut.kompot.view.ControllerContainerFrameLayout

class RootFlowImpl : RootFlow<RootFlowContract.Step, IOData.EmptyInput>(IOData.EmptyInput) {

    override val rootDialogDisplayer by lazy(LazyThreadSafetyMode.NONE) {
        DialogDisplayer(
            loadingDialogDisplayer = DefaultLoadingDialogDisplayer(activity),
            delegates = emptyList()
        )
    }

    override val layoutId = R.layout.flow_root

    override val controllerName = "Root"

    override val component: RootFlowComponent by lazy(LazyThreadSafetyMode.NONE) {
        (activity.application as App)
            .appComponent
            .rootFlowComponent
            .flow(this)
            .build()
    }

    override val flowModel by lazy(LazyThreadSafetyMode.NONE) {
        component.flowModel
    }

    override val containerForModalNavigation: ControllerContainerFrameLayout
        get() = view.findViewById(R.id.containerModal)
}
