package com.example.module_stats.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.presenter.BasePresenter
import com.example.module_stats.fragment.StatsCareFragment
import com.example.module_stats.model.StatsCareModel
import com.example.module_stats.model.StatsModel

class StatsCarePresenter(private val mView: StatsCareFragment) : BasePresenter<StatsCareFragment, StatsCareModel>(mView) {

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
        mModel = StatsCareModel(this)
    }
}