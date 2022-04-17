package com.example.module_calender.ui

import android.content.Context
import android.graphics.Canvas
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MonthView
import android.graphics.Paint
import java.lang.Integer.min


class ToDoWeekView(context: Context?) : MonthView(context) {
    private var mRadius = 0F

    override fun onPreviewHook() {
        mRadius = min(mItemWidth, mItemHeight) / 2F
        super.onPreviewHook()
    }

    override fun onDrawSelected(
        canvas: Canvas?,
        calendar: Calendar?,
        x: Int,
        y: Int,
        hasScheme: Boolean
    ): Boolean {
        val cx = x + mItemWidth / 2F
        val cy = y + mItemHeight / 2F
        if (true == calendar?.isCurrentDay) {
            canvas?.drawCircle(cx, cy, mRadius, mCurDayTextPaint)
        } else {
            canvas?.drawCircle(cx, cy, mRadius, mSelectedPaint)
        }
        return true
    }

    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int) {
//        canvas?.drawCircle(
//            x + mItemWidth - mPadding - mRadio / 2,
//            y + mPadding + mRadio,
//            mRadio,
//            mSchemeBasicPaint
//        )
//        canvas?.drawText(
//            calendar?.getScheme(),
//            x + mItemWidth - mPadding - mRadio / 2 - getTextWidth(calendar.getScheme()) / 2,
//            y + mPadding + mSchemeBaseLine, mTextPaint
//        )
    }


    override fun onDrawText(
        canvas: Canvas?,
        calendar: Calendar?,
        x: Int,
        y: Int,
        hasScheme: Boolean,
        isSelected: Boolean
    ) {
        val cx = x + mItemWidth / 2F
        val top = y - mItemHeight / 6F
        val cy = mTextBaseLine + y + mItemHeight / 10F
        val curDay = calendar!!.day.toString()
        when {
            isSelected -> {
                canvas!!.drawText(curDay, cx, mTextBaseLine + top, mSelectTextPaint)
                canvas.drawText(calendar.lunar, cx, cy, mSelectedLunarTextPaint)
            }
            hasScheme -> {
                canvas!!.drawText(
                    curDay, cx, mTextBaseLine + top,
                    if (calendar.isCurrentMonth) mSchemeTextPaint else mOtherMonthTextPaint
                )
                canvas.drawText(calendar.lunar, cx, cy,
                    if (calendar.isCurrentMonth) mSchemeTextPaint else mOtherMonthTextPaint)
            }
            else -> {
                canvas!!.drawText(
                    curDay, cx, mTextBaseLine + top,
                    if (calendar.isCurrentDay) mCurDayTextPaint else if (calendar.isCurrentMonth) mCurMonthTextPaint else mOtherMonthTextPaint
                )
                canvas.drawText(
                    calendar.lunar, cx, cy,
                    if (calendar.isCurrentDay) mCurDayLunarTextPaint else if (calendar.isCurrentMonth) mCurMonthLunarTextPaint else mOtherMonthLunarTextPaint
                )
            }
        }
    }
}