package com.example.myapplication.ui.components.decorators

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.revolut.decorations.frames.delegates.BaseFrameDecorationDelegate
import java.util.*

class ItemFrameDecorationDelegate(
    @DimenRes private val topPadding: Int = R.dimen.zero,
    @DimenRes private val rightPadding: Int = R.dimen.zero,
    @DimenRes private val bottomPadding: Int = R.dimen.zero,
    @DimenRes private val leftPadding: Int = R.dimen.zero,
    @DimenRes private val topSpace: Int = R.dimen.zero,
    @DimenRes private val rightSpace: Int = R.dimen.zero,
    @DimenRes private val bottomSpace: Int = R.dimen.zero,
    @DimenRes private val leftSpace: Int = R.dimen.zero,
    @DimenRes private val roundRadiusRes: Int = R.dimen.zero,
    @ColorRes private val colorRes: Int
) : BaseFrameDecorationDelegate() {

    private val paddedViews = Collections.newSetFromMap(WeakHashMap<View, Boolean>())

    private val spacedViews = Collections.newSetFromMap(WeakHashMap<View, Boolean>())

    private val rect = RectF()

    private val paint: Paint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = ResourcesCompat.getColor(resources, colorRes, null)
            isAntiAlias = true
        }
    }

    private val radius: Float by lazy {
        resources.getDimensionPixelSize(roundRadiusRes).toFloat()
    }

    override fun onDrawOver(
        canvas: Canvas,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDrawOver(canvas, view, parent, state)
        rect.left = view.left.toFloat()
        rect.top = view.top.toFloat()
        rect.right = view.right.toFloat()
        rect.bottom = view.bottom.toFloat()

        canvas.drawRoundRect(rect, radius, radius, paint)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val paddingTop = resources.getDimensionPixelSize(topPadding)
        val paddingBottom = resources.getDimensionPixelSize(bottomPadding)
        val paddingLeft = resources.getDimensionPixelSize(leftPadding)
        val paddingRight = resources.getDimensionPixelSize(rightPadding)

        val marginTop = resources.getDimensionPixelSize(topSpace)
        val marginBottom = resources.getDimensionPixelSize(bottomSpace)
        val marginLeft = resources.getDimensionPixelSize(leftSpace)
        val marginRight = resources.getDimensionPixelSize(rightSpace)

        if(!spacedViews.contains(view)) {
            outRect.top += marginTop
            outRect.bottom += marginBottom
            outRect.left += marginLeft
            outRect.right += marginRight
            spacedViews.add(view)
        }

        if (!paddedViews.contains(view)) {
            view.setPadding(
                paddingLeft,
                paddingTop,
                paddingRight,
                paddingBottom
            )
            paddedViews.add(view)
        }
    }

    override fun hashCode(): Int {
        var result = topPadding
        result = 31 * result + rightPadding
        result = 31 * result + bottomPadding
        result = 31 * result + leftPadding
        result = 31 * result + roundRadiusRes
        result = 31 * result + colorRes
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemFrameDecorationDelegate

        if (topPadding != other.topPadding) return false
        if (rightPadding != other.rightPadding) return false
        if (bottomPadding != other.bottomPadding) return false
        if (leftPadding != other.leftPadding) return false
        if (topSpace != other.topSpace) return false
        if (rightSpace != other.rightSpace) return false
        if (bottomSpace != other.bottomSpace) return false
        if (leftSpace != other.leftSpace) return false
        if (roundRadiusRes != other.roundRadiusRes) return false
        if (colorRes != other.colorRes) return false
        if (paddedViews != other.paddedViews) return false
        if (rect != other.rect) return false

        return true
    }

}
