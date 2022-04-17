package com.example.todo.application

import android.app.Application
import com.example.database.DBManager
import com.example.library_common.constant.Constants
import com.example.library_common.util.SPUtil

class App: Application() {

    companion object{
        lateinit var mTheme: Theme
    }

    override fun onCreate() {
        super.onCreate()
        val db = DBManager.getInstance(this)!!.getDaoSession(this).appInfoDao
        val list = db.queryBuilder().build().list()
        mTheme = if(list.size == 0 || list[0].theme == Constants.Theme.NORMAL){
            Theme.NORMAL
        }else{
            Theme.CARE
        }
    }


    enum class Theme{
        NORMAL,
        CARE
    }
}