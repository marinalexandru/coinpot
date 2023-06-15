package com.example.myapplication.ui.listing

import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.repositories.TokenMetadataRepository
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.BaseScreenModel
import com.revolut.kompot.navigable.screen.StateMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class ListingScreenModel @Inject constructor(
    stateMapper: StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState>,
    private val newsRepository: NewsRepository,
    private val tokenMetadataRepository: TokenMetadataRepository
) : BaseScreenModel<ListingScreenContract.DomainState, ListingScreenContract.UIState, IOData.EmptyOutput>(
    stateMapper
), ListingScreenContract.ScreenModelApi {

    override val initialState = ListingScreenContract.DomainState(
            newsList = emptyList(),
            tokenList = emptyList()
        )

    private var newsDisposable: Disposable? = null
    private var tokenDisposable: Disposable? = null

    override fun onCreated() {
        super.onCreated()
        subscribeToNews()
        subscribeToTokens()
    }

    override fun onFinished() {
        super.onFinished()
        unsubscribeFromNews()
        unsubscribeFromTokens()
    }

    private fun subscribeToNews() {
        newsDisposable = newsRepository.observeNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                updateState {
                    this.copy(newsList = data.content ?: emptyList())
                }
            }
    }

    private fun subscribeToTokens() {
        tokenDisposable = tokenMetadataRepository.observeTokens()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                updateState {
                    this.copy(tokenList = data.content ?: emptyList())
                }
            }
    }

    private fun unsubscribeFromNews() {
        newsDisposable?.dispose()
    }

    private fun unsubscribeFromTokens() {
        tokenDisposable?.dispose()
    }

}
