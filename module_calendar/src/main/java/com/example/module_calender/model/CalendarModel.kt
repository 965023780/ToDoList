package com.example.module_calender.model

import android.content.Context
import android.content.Entity
import android.util.Log
import com.example.database.DBManager
import com.example.database.TaskEntity
import com.example.library_base.contract.IBaseContract
import com.example.library_base.model.BaseModel
import com.example.library_common.util.DateUtil
import com.example.module_calender.presenter.CalendarPresenter

class CalendarModel(private val mPresenter: CalendarPresenter): BaseModel<MutableList<TaskEntity>>(mPresenter) {

    public override fun loadData(context: Context) {
        val db = DBManager.getInstance(context)!!.getDaoSession(context).taskEntityDao
        mData = db.queryBuilder().build().list()
    }

    fun requestData(target: Long, context: Context){
        loadAndDispatchData(target, context)
    }

    private fun loadAndDispatchData(target: Long, context: Context){
        val db = DBManager.getInstance(context)!!.getDaoSession(context).taskEntityDao
        val list = ArrayList<TaskEntity>()
        db.queryBuilder().build().list().forEach {
            val days = DateUtil.calDiffDays(it.time, target).toInt()
            if(days < 1){
                list.add(it)
            }
        }
        mData = list.toMutableList()
        mPresenter.notifyUI(IBaseContract.DATA_SUCCESS)
    }

}