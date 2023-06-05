package com.example.myapplication.di.modules

import com.example.myapplication.ui.listing.ListingScreenContract
import com.example.myapplication.ui.listing.ListingScreenModel
import com.example.myapplication.ui.listing.ListingStateMapper
import com.revolut.kompot.di.scope.ScreenScope
import com.revolut.kompot.di.screen.BaseScreenModule
import com.revolut.kompot.navigable.screen.StateMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class ListingScreenModule : BaseScreenModule {

    @Binds
    @ScreenScope
    abstract fun provideMapper(mapper: ListingStateMapper): StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState>

    @Binds
    @ScreenScope
    abstract fun provideScreenModel(model: ListingScreenModel): ListingScreenContract.ScreenModelApi
}
