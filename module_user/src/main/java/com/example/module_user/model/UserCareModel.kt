package com.example.module_user.model

import android.content.Context
import com.example.library_base.model.BaseModel
import com.example.module_user.R
import com.example.module_user.bean.UserSettingBean
import com.example.module_user.presenter.UserCarePresenter

class UserCareModel(mPresenter: UserCarePresenter): BaseModel<MutableList<UserSettingBean>>(mPresenter) {

    public override fun loadData(context: Context) {
        val list = context.resources.getStringArray(R.array.user_setting_text)
        mData = MutableList(list.size){
            UserSettingBean(-1, list[it])
        }
    }

}