package com.example.myapplication.ui.listing

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ScreenListingBinding
import com.example.myapplication.di.components.RootFlowComponent
import com.example.myapplication.ui.components.decorators.ItemDecorators
import com.example.myapplication.ui.components.delegates.HorizontalListDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleShimmerDelegate
import com.example.myapplication.ui.components.delegates.TitleH1Delegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageDelegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageShimmerDelegate
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
                    delegates = listOf(
                        TitleAndSubtitleOverImageDelegate(
                            onTapListener = { model -> onTitleOverImageTap(model) },
                            frameDecoration = ItemDecorators.listItemLeftSpacedDecorationDelegate
                        ),
                        TitleAndSubtitleOverImageShimmerDelegate(
                            frameDecoration = ItemDecorators.listItemLeftSpacedDecorationDelegate
                        ),
                    )
                ),
                ImageWithTitleAndSubtitleDelegate(
                    onTapListener = { model -> onImageWithTitleAndSubtitleTap(model) },
                    frameDecoration = ItemDecorators.listItemPaddedBottomSpacedAndRoundedDecorationDelegate
                ),
                ImageWithTitleAndSubtitleShimmerDelegate(
                    frameDecoration = ItemDecorators.listItemPaddedBottomSpacedAndRoundedDecorationDelegate
                )
            )
        )
    }

    override fun onScreenViewCreated(view: View) {
        super.onScreenViewCreated(view)
       configScreen()
        setControls()
    }

    private fun configScreen() {
        binding.rvContent.adapter = adapter
        binding.rvContent.layoutManager = LinearLayoutManager(
            activity.applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvContent.addItemDecoration(DelegatesFrameItemDecoration())
    }

    private fun setControls() {
        binding.srlContent.setOnRefreshListener {
            screenModel.reload()
        }
    }

    override fun bindScreen(
        uiState: ListingScreenContract.UIState,
        payload: ScreenStates.UIPayload?
    ) = with(binding) {

        if(srlContent.isRefreshing) {
            srlContent.isRefreshing = false
        }

        adapter.setItems(
            listOf(
                uiState.newsTitle,
                uiState.newsList,
                uiState.cryptoTitle,
                *uiState.cryptoList.toTypedArray(),
                *uiState.cryptoListShimmer.toTypedArray()
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
