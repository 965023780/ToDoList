package com.example.library_common.service

import android.accessibilityservice.AccessibilityService
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.example.library_common.util.AccessibilityUtil
import java.util.*
import kotlin.collections.HashMap

class ToDoAccessibilityService : AccessibilityService() {
    private val TAG = "accessibility"
    private lateinit var tts: TextToSpeech
    private val util by lazy{
        AccessibilityUtil()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        when (event?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                speak(event)
                Log.i(TAG, "TYPE_WINDOW_STATE_CHANGED")
            }
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                speak(event)
                Log.i(TAG, "TYPE_VIEW_CLICKED")
            }
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                speak(event)
                Log.i(TAG, "TYPE_VIEW_FOCUSED")
            }

            AccessibilityEvent.TYPE_ANNOUNCEMENT -> {
                TODO()
            }
            AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT -> {
                TODO()
            }
            AccessibilityEvent.TYPE_GESTURE_DETECTION_END -> {
                TODO()
            }
            AccessibilityEvent.TYPE_GESTURE_DETECTION_START -> {
                TODO()
            }
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END -> {
                TODO()
            }
            AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START -> {
                TODO()
            }
            AccessibilityEvent.TYPE_TOUCH_INTERACTION_END -> {
                TODO()
            }
            AccessibilityEvent.TYPE_TOUCH_INTERACTION_START -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_HOVER_ENTER -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_HOVER_EXIT -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_LONG_CLICKED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_SCROLLED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_SELECTED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY -> {
                TODO()
            }
            AccessibilityEvent.TYPE_WINDOWS_CHANGED -> {
                TODO()
            }
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                TODO()
            }
        }
    }

    //IF地狱
    private fun translate(content: String): String{
        when (content) {
            "日历" -> {
                return "now you are on calendar"
            }
            "任务" -> {
                return "now you are on task"
            }
            "统计" -> {
                return "now you are on stats"
            }
            "我的" ->{
                return "now you are on user information"
            }
        }
        return ""
    }


    private fun speak(event: AccessibilityEvent){
        val content = event.contentDescription.toString()
        val speakContent = translate(content)
        tts.speak(speakContent,TextToSpeech.QUEUE_FLUSH, HashMap())
    }

    override fun onInterrupt() {
    }

    override fun onServiceConnected(){
        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            if(it == 1){
                tts.language = Locale.CHINA //不支持中文
                Log.i(TAG, "tts init success")
            }
        })
    }

//    override fun onServiceConnected() {
//        val info = AccessibilityServiceInfo().apply {
//            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED
//            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
//            notificationTimeout = 100
//        }
//        this.serviceInfo = info
//    }
}

