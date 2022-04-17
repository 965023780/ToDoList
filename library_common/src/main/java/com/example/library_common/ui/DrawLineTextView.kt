package com.example.library_common.ui

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet

class DrawLineTextView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    fun setDrawLineFlag(flag: Boolean){
        if(flag) {
            paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
        }else{
            paint.flags = Paint.ANTI_ALIAS_FLAG
        }
    }
}