package com.example.myapplication.ui.listing

import com.example.myapplication.data.repositories.NewsRepository
import com.example.myapplication.data.repositories.TokenRepository
import com.example.myapplication.data.services.IntentDispatchService
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageDelegate
import com.example.myapplication.ui.state.LoadableState
import com.example.myapplication.ui.state.LoadableState.Companion.toLoadableState
import com.example.myapplication.ui.utils.TextUtils.computeTokenUrlFrom
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
    private val tokenRepository: TokenRepository,
    private val intentDispatchService: IntentDispatchService
) : BaseScreenModel<ListingScreenContract.DomainState, ListingScreenContract.UIState, IOData.EmptyOutput>(
    stateMapper
), ListingScreenContract.ScreenModelApi {

    override val initialState = ListingScreenContract.DomainState(
        news = LoadableState.Loading(),
        tokens = LoadableState.Loading()
    )

    private var newsDisposable: Disposable? = null
    private var tokenDisposable: Disposable? = null

    override fun onCreated() {
        super.onCreated()
        subscribeToNews()
        subscribeToTokens()
    }

    override fun onTitleOverImageTap(model: TitleAndSubtitleOverImageDelegate.Model) {
        val news = state.news.data?.find { it.id == model.listId } ?: return

        intentDispatchService.openWebPage(news.sourceUrl)
    }

    override fun onImageWithTitleAndSubtitleTap(model: ImageWithTitleAndSubtitleDelegate.Model) {
        val token = state.tokens.data?.find { it.id == model.listId } ?: return

        intentDispatchService.openWebPage(computeTokenUrlFrom(key = token.slug))
    }

    override fun onFinished() {
        super.onFinished()
        unsubscribeFromNews()
        unsubscribeFromTokens()
    }

    override fun reload() {
        unsubscribeFromNews()
        unsubscribeFromTokens()
        subscribeToNews()
        subscribeToTokens()
    }

    private fun subscribeToNews() {
        newsDisposable = newsRepository.observeNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                updateState {
                    this.copy(news = data.toLoadableState())
                }
            }
    }

    private fun subscribeToTokens() {
        tokenDisposable = tokenRepository.observeTokens()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                updateState {
                    this.copy(tokens = data.toLoadableState())
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
