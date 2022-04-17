package com.example.module_calender.ui

import android.content.Context
import com.haibin.calendarview.WeekBar

import android.view.LayoutInflater
import android.widget.TextView
import com.example.library_common.util.DensityUtil
import com.example.module_calender.R
import com.haibin.calendarview.Calendar


class ToDoWeekBarView(context: Context) : WeekBar(context) {
    private var mPreSelectedIndex = 0

   init{
       LayoutInflater.from(context).inflate(R.layout.calendar_todo_week_bar, this, true)
       val padding: Int = DensityUtil.dp2Px(context, 10F)
       setPadding(padding, 0, padding, 0)
   }

    override fun onDateSelected(calendar: Calendar, weekStart: Int, isClick: Boolean) {
        getChildAt(mPreSelectedIndex).isSelected = false
        val viewIndex = getViewIndexByCalendar(calendar, weekStart)
        getChildAt(viewIndex).isSelected = true
        mPreSelectedIndex = viewIndex
    }


    override fun onWeekStartChange(weekStart: Int) {
        for (i in 0 until childCount) {
            (getChildAt(i) as TextView).text = getWeekString(i, weekStart)
        }
    }

    private fun getWeekString(index: Int, weekStart: Int): String {
        val weeks = context.resources.getStringArray(R.array.todo_week_bar_view)
        if (weekStart == 1) {
            return weeks[index]
        }
        if (weekStart == 2) {
            return weeks[if(index == 6)  0 else index + 1]
        }
        return weeks[if(index == 6)  0 else index - 1]
    }

}