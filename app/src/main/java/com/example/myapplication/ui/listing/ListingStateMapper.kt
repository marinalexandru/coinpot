package com.example.myapplication.ui.listing

import com.example.myapplication.ui.listing.delegates.NewsBoxItemDelegate
import com.revolut.kompot.navigable.screen.StateMapper
import javax.inject.Inject

class ListingStateMapper @Inject constructor() :
    StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState> {

    override fun mapState(domainState: ListingScreenContract.DomainState) =
        ListingScreenContract.UIState(domainState.title, domainState.newsList.map {
            NewsBoxItemDelegate.NewsBoxItem(
                listId = it.sourceUrl, //TODO Replace with database ID
                text = it.title,
                imageUrl = it.cover
            )
        })
}
