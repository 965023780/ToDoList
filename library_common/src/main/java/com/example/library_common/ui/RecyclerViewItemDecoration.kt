package com.example.library_common.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(private val left: Int,
                                 private val top: Int,
                                 private val right: Int,
                                 private val bottom: Int): RecyclerView.ItemDecoration() {
        private var mDividerColor = 0
        private var mDividerHeight = 0
        private var mDividerMarginHeight = 0
        private val mPaint by lazy {
            Paint()
        }

        constructor(left: Int, top: Int, right: Int, bottom: Int,
            dividerColor: Int, dividerHeight: Int, dividerMarginHeight: Int
        ):this(left, top, right, bottom) {
            mDividerColor = dividerColor
            mDividerHeight = dividerHeight
            mDividerMarginHeight = dividerMarginHeight
            mPaint.color = dividerColor
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            if (left == 0 && top == 0 && right == 0 && bottom == 0) {
                return
            }
            outRect.left = left
            outRect.top = top
            outRect.right = right
            outRect.bottom = bottom
        }


        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            val childCount = parent.childCount
            for (i in 0 until childCount){
                val child = parent.getChildAt(i)
                val left = child.left
                val top = child.bottom + mDividerMarginHeight
                val right = child.right
                val bottom = top + mDividerHeight
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
}