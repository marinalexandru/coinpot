package com.example.myapplication.ui.listing

import com.example.myapplication.data.repositories.NewsRepository
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.BaseScreenModel
import com.revolut.kompot.navigable.screen.StateMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class ListingScreenModel @Inject constructor(
    private val stateMapper: StateMapper<ListingScreenContract.DomainState, ListingScreenContract.UIState>,
    inputData: ListingScreenContract.InputData,
    private val newsRepository: NewsRepository
) : BaseScreenModel<ListingScreenContract.DomainState, ListingScreenContract.UIState, IOData.EmptyOutput>(
    stateMapper
), ListingScreenContract.ScreenModelApi {

    override val initialState = ListingScreenContract.DomainState(inputData.title, emptyList())

    private var newsDisposable: Disposable? = null

    override fun onCreated() {
        super.onCreated()
        subscribeToNews()
    }

    override fun onFinished() {
        super.onFinished()
        unsubscribeFromNews()
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

    private fun unsubscribeFromNews() {
        newsDisposable?.dispose()
    }

}
