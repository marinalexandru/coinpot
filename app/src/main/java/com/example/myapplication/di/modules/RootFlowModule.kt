package com.example.myapplication.di.modules

import com.example.myapplication.ui.root.RootFlowContract
import com.example.myapplication.ui.root.RootFlowModel
import com.revolut.kompot.di.flow.BaseFlowModule
import com.revolut.kompot.di.scope.FlowScope
import dagger.Binds
import dagger.Module

@Module
internal abstract class RootFlowModule : BaseFlowModule {
    @Binds
    @FlowScope
    abstract fun provideFlowModel(flowModel: RootFlowModel): RootFlowContract.FlowModelApi
}
