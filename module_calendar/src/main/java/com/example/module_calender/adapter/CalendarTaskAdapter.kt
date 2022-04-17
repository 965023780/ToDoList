package com.example.module_calender.adapter

import android.graphics.Color
import android.view.View
import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.database.DBManager
import com.example.database.TaskEntity
import com.example.library_common.ui.DrawLineTextView
import com.example.module_calender.R

class CalendarTaskAdapter(layoutResId: Int, data: MutableList<TaskEntity>?) :
        BaseQuickAdapter<TaskEntity, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: TaskEntity) {
        val textView = holder.getView<DrawLineTextView>(R.id.item_tv_content)
        val view = holder.itemView
        val checkBox = holder.getView<CheckBox>(R.id.item_check)
        textView.text = item.content
        checkBox.isChecked = item.isCompleted()
        if(checkBox.isChecked){
            textView.setDrawLineFlag(true)
            textView.setTextColor(Color.GRAY)
        }
        view.isFocusable = true
        view.contentDescription = "任务${item.content}"
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                textView.setDrawLineFlag(true)
                textView.setTextColor(Color.GRAY)
            }else{
                textView.setDrawLineFlag(false)
                textView.setTextColor(Color.BLACK)
            }
            textView.invalidate()
            item.setIsCompleted(isChecked)
            DBManager.getInstance(context)!!.getDaoSession(context).taskEntityDao.update(item)
        }
    }

}