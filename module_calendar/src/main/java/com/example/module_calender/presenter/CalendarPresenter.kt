package com.example.module_calender.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.presenter.BasePresenter
import com.example.module_calender.adapter.CalendarTaskAdapter
import com.example.module_calender.fragment.CalendarFragment
import com.example.module_calender.model.CalendarModel

class CalendarPresenter(private val mView: CalendarFragment) :
    BasePresenter<CalendarFragment, CalendarModel>(mView) {

    override fun requestData() {
        mView.context?.let {
            mModel.requestData(it)
        }
    }

    fun requestData(target: Long) {
        mView.context?.let {
            mModel.requestData(target, it)
        }
    }

    override fun notifyUI(type: Int) {
        if (IBaseContract.DATA_SUCCESS == type) {
            val view = getView()
            view?.showContent(mModel.mData)
        }
    }


    override fun initModel() {
        mModel = CalendarModel(this)
    }
}