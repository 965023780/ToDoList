package com.example.module_stats.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.presenter.BasePresenter
import com.example.module_stats.fragment.StatsFragment
import com.example.module_stats.model.StatsModel

class StatsPresenter(private val mView: StatsFragment) : BasePresenter<StatsFragment, StatsModel>(mView) {

    override fun requestData() {
        mView.context?.let {
            mModel.requestData(it)
        }
    }

    override fun notifyUI(type: Int) {
        if (IBaseContract.DATA_SUCCESS == type) {
            val view = getView()
            view?.showContent(mModel.mData)
        }
    }


    override fun initModel() {
        mModel = StatsModel(this)
    }
}