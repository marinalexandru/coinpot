package com.example.myapplication.ui.listing

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ScreenListingBinding
import com.example.myapplication.di.components.RootFlowComponent
import com.example.myapplication.ui.shared.delegates.HorizontalListDelegate
import com.example.myapplication.ui.shared.delegates.ImageWithTitleAndSubtitleDelegate
import com.example.myapplication.ui.shared.delegates.SectionTitleDelegate
import com.example.myapplication.ui.shared.delegates.TitleOverImageDelegate
import com.revolut.decorations.dividers.DelegatesDividerItemDecoration
import com.revolut.decorations.dividers.delegates.DividerDecorationDelegate
import com.revolut.decorations.dividers.delegates.lines.LineDividerDecorationDelegate
import com.revolut.decorations.overlay.DelegatesOverlayItemDecoration
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
                SectionTitleDelegate(),
                HorizontalListDelegate(
                    delegate = TitleOverImageDelegate { model -> onTitleOverImageTap(model) },
                    klass = TitleOverImageDelegate.Model::class.java
                ),
                ImageWithTitleAndSubtitleDelegate(
                    onTapListener = { model -> onImageWithTitleAndSubtitleTap(model) }
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
        adapter.setItems(
            listOf(
                uiState.newsSectionTitle,
                uiState.newsHorizontalGallery,
                uiState.cryptoSectionTitle,
                *uiState.cryptoList.toTypedArray()
            )
        )
    }

    private fun onTitleOverImageTap(model: TitleOverImageDelegate.Model) {

    }

    private fun onImageWithTitleAndSubtitleTap(model: ImageWithTitleAndSubtitleDelegate.Model) {

    }
}
