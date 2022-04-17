package com.example.todo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.library_base.model.BaseModel
import com.example.library_base.presenter.BasePresenter
import com.example.library_base.view.BaseFragment
import com.example.module_calender.fragment.CalendarCareFragment
import com.example.module_calender.fragment.CalendarFragment
import com.example.module_stats.fragment.StatsCareFragment
import com.example.module_stats.fragment.StatsFragment
import com.example.module_task.fragment.TaskCareFragment
import com.example.module_task.fragment.TaskFragment
import com.example.module_user.fragment.UserCareFragment
import com.example.module_user.fragment.UserFragment
import com.example.todo.application.App

class ContainerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentArray =
        if (App.Theme.NORMAL == App.mTheme) {
            arrayListOf(TaskFragment(), CalendarFragment(), StatsFragment(), UserFragment())
        } else {
            arrayListOf(TaskCareFragment(), CalendarCareFragment(), StatsCareFragment(), UserCareFragment())
        }

    override fun getItemCount(): Int {
        return fragmentArray.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArray[position]
    }
}