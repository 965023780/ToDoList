package com.example.module_task.model

import android.content.Context
import com.example.database.DBManager
import com.example.database.TaskEntity
import com.example.library_base.model.BaseModel
import com.example.module_task.presenter.TaskPresenter

class TaskModel(mPresenter: TaskPresenter): BaseModel<MutableList<TaskEntity>>(mPresenter) {

    public override fun loadData(context: Context) {
        val db = DBManager.getInstance(context)!!.getDaoSession(context).taskEntityDao
        mData = db.queryBuilder().build().list()
    }

}