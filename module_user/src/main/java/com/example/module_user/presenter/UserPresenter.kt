package com.example.module_user.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.presenter.BasePresenter
import com.example.module_user.adapter.UserAdapter
import com.example.module_user.fragment.UserFragment
import com.example.module_user.model.UserModel

class UserPresenter(private val mView: UserFragment):
    BasePresenter<UserFragment, UserModel>(mView) {
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
        mModel = UserModel(this)
    }
}