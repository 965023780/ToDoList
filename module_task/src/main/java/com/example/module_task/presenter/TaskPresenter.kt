package com.example.module_task.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.presenter.BasePresenter
import com.example.module_task.adapter.TaskAdapter
import com.example.module_task.fragment.TaskFragment
import com.example.module_task.model.TaskModel

class TaskPresenter(private val mView: TaskFragment):
    BasePresenter<TaskFragment, TaskModel>(mView) {
    override fun requestData() {
        mView.context?.let {
            mModel.requestData(it)
        }
    }

    override fun notifyUI(type: Int) {
        if(IBaseContract.DATA_SUCCESS == type){
            val view = getView()
            view?.showContent(mModel.mData)
        }
    }


    override fun initModel() {
        mModel = TaskModel(this)
    }
}