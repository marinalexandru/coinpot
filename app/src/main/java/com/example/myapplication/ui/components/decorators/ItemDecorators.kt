package com.example.myapplication.ui.components.decorators

import com.example.myapplication.R

object ItemDecorators {

    val listItemSpacedAllDecorationDelegate
        get() =
            ItemFrameDecorationDelegate(
                topPadding = R.dimen.vertical_item_spacing_double,
                bottomPadding = R.dimen.vertical_item_spacing,
                leftPadding = R.dimen.horizontal_item_spacing,
                rightPadding = R.dimen.horizontal_item_spacing,
                colorRes = android.R.color.transparent
            )

    val listItemLeftSpacedDecorationDelegate
        get() =
            ItemFrameDecorationDelegate(
                leftPadding = R.dimen.horizontal_item_spacing,
                colorRes = android.R.color.transparent
            )

    val listItemPaddedBottomSpacedAndRoundedDecorationDelegate
        get() =
            ItemFrameDecorationDelegate(
                topPadding = R.dimen.vertical_item_spacing,
                bottomPadding = R.dimen.vertical_item_spacing,
                leftPadding = R.dimen.horizontal_item_spacing,
                rightPadding = R.dimen.horizontal_item_spacing_half,
                bottomSpace = R.dimen.vertical_item_spacing,
                leftSpace = R.dimen.horizontal_item_spacing,
                rightSpace = R.dimen.horizontal_item_spacing,
                roundRadiusRes = R.dimen.item_round_radius,
                colorRes = R.color.white_faded
            )

}
