package com.example.library_base.contract

interface IBaseContract {
    companion object {
        val DATA_SUCCESS = 1
        val DATA_FAILE = 0
    }

    interface IView{
        val mPresenter: IPresenter

        fun <T> showContent(data: T)
    }

    interface IPresenter{

        fun requestData()

        fun notifyUI(type: Int)

        fun initModel()
    }
}