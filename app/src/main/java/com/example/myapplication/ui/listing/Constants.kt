package com.example.myapplication.ui.listing

import com.example.myapplication.ui.components.delegates.ImageWithTitleAndSubtitleShimmerDelegate
import com.example.myapplication.ui.components.delegates.TitleAndSubtitleOverImageShimmerDelegate

object Constants {
    private const val NEWS_SHIMMER_ID_PREFIX = "NEWS_SHIMMER_ID_"
    private const val CRYPTO_SHIMMER_ID_PREFIX = "CRYPTO_SHIMMER_ID_"

    val NEWS_SHIMMER_LIST = List(2) {
        TitleAndSubtitleOverImageShimmerDelegate.Model(NEWS_SHIMMER_ID_PREFIX + it)
    }

    val CRYPTO_SHIMMER_LIST =List(6) {
        ImageWithTitleAndSubtitleShimmerDelegate.Model(CRYPTO_SHIMMER_ID_PREFIX + it)
    }

}
