package com.example.module_stats.model

import android.content.Context
import com.example.database.DBManager
import com.example.library_base.model.BaseModel
import com.example.library_common.util.DateUtil
import com.example.module_stats.bean.StatsBean
import com.example.module_stats.presenter.StatsPresenter
import kotlin.math.floor

class StatsModel(mPresenter: StatsPresenter): BaseModel<StatsBean>(mPresenter) {

    public override fun loadData(context: Context) {
        val db = DBManager.getInstance(context)!!.getDaoSession(context).taskEntityDao
        val list = db.queryBuilder().build().list()
        var todayCompletedNumber = 0
        var weekCompletedNumber = 0
        var monthCompletedNumber = 0
        val totalCompletedNumber = list.size
        val recentCompletedNumber = Array<Int>(7){
            0
        }
        for(entity in list){
            val days = DateUtil.calDiffDays(entity.time, System.currentTimeMillis()).toInt()
            if(days < 1){
                todayCompletedNumber++
            }
            if(days < 7){
                recentCompletedNumber[days]++
                weekCompletedNumber++
            }
            if(days < 30){
                monthCompletedNumber++
            }
        }
        mData = StatsBean(todayCompletedNumber, weekCompletedNumber, monthCompletedNumber, totalCompletedNumber, recentCompletedNumber)
    }



}