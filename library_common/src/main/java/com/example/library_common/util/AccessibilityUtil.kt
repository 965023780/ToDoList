package com.example.library_common.util

import android.view.accessibility.AccessibilityNodeInfo

import android.accessibilityservice.AccessibilityService

import android.os.Build

import android.annotation.SuppressLint

import android.content.Context
import android.provider.Settings
import android.text.TextUtils


class AccessibilityUtil {
    var isRunning = false

    fun isRunning(context: Context, service: Class<*>): Boolean {
        val enable: Int = Settings.Secure.getInt(context.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, 0)
        if (enable != 1){
            return false
        }
        val services: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        if (!TextUtils.isEmpty(services)) {
            val split = TextUtils.SimpleStringSplitter(':')
            split.setString(services)
            while (split.hasNext()) {
                if (split.next().equals(context.packageName.toString() + "/" + service.name, true)){
                    return true
                }
            }
        }
        return false
    }



}