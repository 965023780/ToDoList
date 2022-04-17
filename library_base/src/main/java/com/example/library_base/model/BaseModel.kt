package com.example.library_base.model

import android.content.Context
import com.example.library_base.contract.IBaseContract

abstract class BaseModel<T>(private val mPresenter: IBaseContract.IPresenter) {
    var mData: T? = null

    protected abstract fun loadData(context: Context)

    fun requestData(context: Context){
        loadData(context)
        disposeData()
    }

    private fun disposeData() {
        if(mData == null){
            mPresenter.notifyUI(IBaseContract.DATA_FAILE)
        }else{
            mPresenter.notifyUI(IBaseContract.DATA_SUCCESS)
        }
    }
}