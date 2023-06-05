package com.example.myapplication.di.components

import com.example.myapplication.di.modules.RootFlowModule
import com.example.myapplication.ui.root.RootFlowContract
import com.revolut.kompot.di.flow.BaseFlowComponent
import com.revolut.kompot.di.scope.FlowScope
import dagger.Subcomponent

@FlowScope
@Subcomponent(
    modules = [RootFlowModule::class]
)
interface RootFlowComponent : BaseFlowComponent {
    val flowModel: RootFlowContract.FlowModelApi

    fun getListingScreenComponentBuilder(): ListingScreenComponent.Builder

    @Subcomponent.Builder
    interface Builder : BaseFlowComponent.Builder<RootFlowComponent, Builder>
}
