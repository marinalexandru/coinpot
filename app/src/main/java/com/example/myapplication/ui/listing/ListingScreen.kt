package com.example.myapplication.ui.listing

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ScreenListingBinding
import com.example.myapplication.di.components.RootFlowComponent
import com.example.myapplication.ui.components.decorators.ItemDecorators
import com.example.myapplication.ui.components.delegates.HorizontalListDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.components.delegates.TitleH1Delegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageDelegate
import com.revolut.decorations.frames.DelegatesFrameItemDecoration
import com.revolut.kompot.common.IOData
import com.revolut.kompot.navigable.screen.BaseScreen
import com.revolut.kompot.navigable.screen.ScreenStates
import com.revolut.kompot.navigable.utils.viewBinding
import com.revolut.rxdiffadapter.RxDiffAdapter

class ListingScreen(title: String) :
    BaseScreen<ListingScreenContract.UIState, ListingScreenContract.InputData, IOData.EmptyOutput>(
        ListingScreenContract.InputData(title)
    ) {

    override val layoutId = R.layout.screen_listing

    private val binding by viewBinding(ScreenListingBinding::bind)

    override val screenModel by lazy(LazyThreadSafetyMode.NONE) {
        screenComponent.screenModel
    }

    override val screenComponent by lazy(LazyThreadSafetyMode.NONE) {
        (flowComponent as RootFlowComponent)
            .getListingScreenComponentBuilder()
            .screen(this)
            .inputData(inputData)
            .build()
    }

    private val adapter by lazy {
        RxDiffAdapter(
            async = true,
            delegates = listOf(
                TitleH1Delegate(
                    frameDecoration = ItemDecorators.listItemSpacedAllDecorationDelegate
                ),
                HorizontalListDelegate(
                    delegate = TitleAndSubtitleOverImageDelegate(
                        onTapListener = { model -> onTitleOverImageTap(model) },
                        frameDecoration = ItemDecorators.listItemLeftSpacedDecorationDelegate
                    ),
                    klass = TitleAndSubtitleOverImageDelegate.Model::class.java
                ),
                ImageWithTitleAndSubtitleDelegate(
                    onTapListener = { model -> onImageWithTitleAndSubtitleTap(model) },
                    frameDecoration = ItemDecorators.listItemPaddedBottomSpacedAndRoundedDecorationDelegate
                )
            )
        )
    }

    override fun bindScreen(
        uiState: ListingScreenContract.UIState,
        payload: ScreenStates.UIPayload?
    ) = with(binding) {
        rvContent.adapter = adapter
        rvContent.layoutManager = LinearLayoutManager(
            activity.applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        rvContent.addItemDecoration(DelegatesFrameItemDecoration())

        adapter.setItems(
            listOf(
                uiState.newsTitle,
                uiState.newsList,
                uiState.cryptoTitle,
                *uiState.cryptoList.toTypedArray()
            )
        )
    }

    private fun onTitleOverImageTap(model: TitleAndSubtitleOverImageDelegate.Model) {
        screenModel.onTitleOverImageTap(model)
    }

    private fun onImageWithTitleAndSubtitleTap(model: ImageWithTitleAndSubtitleDelegate.Model) {
        screenModel.onImageWithTitleAndSubtitleTap(model)
    }
}
