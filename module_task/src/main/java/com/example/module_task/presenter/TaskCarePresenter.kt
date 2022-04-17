package com.example.module_task.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.presenter.BasePresenter
import com.example.module_task.fragment.TaskCareFragment
import com.example.module_task.model.TaskCareModel

class TaskCarePresenter(private val mView: TaskCareFragment):
    BasePresenter<TaskCareFragment, TaskCareModel>(mView) {
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
        mModel = TaskCareModel(this)
    }
}