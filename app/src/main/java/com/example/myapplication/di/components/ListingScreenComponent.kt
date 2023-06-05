package com.example.myapplication.di.components

import com.example.myapplication.di.modules.ListingScreenModule
import com.example.myapplication.ui.listing.ListingScreenContract
import com.revolut.kompot.di.scope.ScreenScope
import com.revolut.kompot.di.screen.BaseScreenComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ScreenScope
@Subcomponent(
    modules = [ListingScreenModule::class]
)
interface ListingScreenComponent : BaseScreenComponent {

    val screenModel: ListingScreenContract.ScreenModelApi

    @Subcomponent.Builder
    interface Builder : BaseScreenComponent.Builder<ListingScreenComponent, Builder> {
        @BindsInstance
        fun inputData(inputData: ListingScreenContract.InputData): Builder
    }
}
