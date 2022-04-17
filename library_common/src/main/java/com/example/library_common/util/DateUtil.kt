package com.example.library_common.util

import kotlin.math.abs
import kotlin.math.floor

object DateUtil {
    fun calDiffDays(entityTime: Long, targetTime: Long): Float {
        val timeDiff = abs(targetTime - entityTime)
        return floor(timeDiff / (24F * 3600F * 1000F))
    }
}