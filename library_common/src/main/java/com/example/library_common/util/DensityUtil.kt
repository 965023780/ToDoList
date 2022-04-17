package com.example.library_common.util

import android.content.Context

object DensityUtil {

    fun dp2Px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}