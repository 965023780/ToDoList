package com.example.module_user.adapter

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.database.AppInfo
import com.example.database.AppInfoDao
import com.example.database.DBManager
import com.example.library_common.constant.Constants
import com.example.module_user.R
import com.example.module_user.bean.UserSettingBean
import java.lang.ref.SoftReference

class UserAdapter(mContext: Context, layoutResId: Int, data: MutableList<UserSettingBean>?) :
    BaseQuickAdapter<UserSettingBean, BaseViewHolder>(layoutResId, data), View.OnClickListener {
    override fun convert(holder: BaseViewHolder, item: UserSettingBean) {
        val textView = holder.getView<AppCompatTextView>(R.id.user_tv_item)
        textView.text = item.content
        textView.setOnClickListener(this)

    }

    private var contextRef: SoftReference<Context> = SoftReference(mContext)

    private fun setAppInfo(
        db: AppInfoDao,
        list: MutableList<AppInfo>,
        theme: String,
        chartColor: String
    ) {
        if (list.size == 0) {
            db.insert(AppInfo(1, theme, chartColor))
        } else {
            val appInfo = list[0]
            if (Constants.Theme.THEME != theme) {
                appInfo.theme = theme
            }
            if (Constants.ChartColor.CHARTCOLOR != chartColor) {
                appInfo.chartColor = chartColor
            }
            db.update(appInfo)
        }
    }

    override fun onClick(v: View?) {
        val context = contextRef.get()
        context?.let {
            val db = DBManager.getInstance(it)!!.getDaoSession(it).appInfoDao
            val list = db.queryBuilder().build().list()
            when ((v as AppCompatTextView?)?.text) {
                "普通模式" -> {
                    setAppInfo(db, list, Constants.Theme.NORMAL, Constants.ChartColor.CHARTCOLOR)
                    Toast.makeText(context, "重新启动app激活", Toast.LENGTH_SHORT).show()
                }
                "关爱模式" -> {
                    setAppInfo(db, list, Constants.Theme.CARE, Constants.ChartColor.CHARTCOLOR)
                    Toast.makeText(context, "重新启动app激活", Toast.LENGTH_SHORT).show()
                }
                "蓝色图表" -> {
                    setAppInfo(db, list, Constants.Theme.THEME, Constants.ChartColor.DEFAULT)
                    Toast.makeText(context, "设置成功，图表为蓝色", Toast.LENGTH_SHORT).show()
                }
                "红色图表" -> {
                    setAppInfo(db, list, Constants.Theme.THEME, Constants.ChartColor.RED)
                    Toast.makeText(context, "设置成功，图表为红色", Toast.LENGTH_SHORT).show()
                }
                "黄色图表" -> {
                    setAppInfo(db, list, Constants.Theme.THEME, Constants.ChartColor.YELLOW)
                    Toast.makeText(context, "设置成功，图表为黄色", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "尚未完成", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}