package com.example.module_calender.fragment

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.database.TaskEntity
import com.example.library_base.view.BaseFragment
import com.example.library_common.router.RouterFragmentPath
import com.example.module_calender.R
import com.example.module_calender.adapter.CalendarTaskAdapter
import com.example.module_calender.presenter.CalendarPresenter
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView

@Route(path = RouterFragmentPath.CALENDAR)
class CalendarFragment: BaseFragment<CalendarPresenter>() {
    private val adapter = CalendarTaskAdapter(R.layout.calendar_item_task, null)
    override val mPresenter = CalendarPresenter(this)
    override fun init() {
        initCalendar()
        initRecyclerView()
        initPresenter()
    }

    private fun initPresenter(){
        mPresenter.requestData()
    }

    private fun initCalendar(){
        val calendar = rootView.findViewById<CalendarView>(R.id.calendar)
        calendar.setWeeColor(Color.WHITE, Color.BLACK)
        calendar.scrollToCurrent()
        calendar.setOnCalendarSelectListener(object: CalendarView.OnCalendarSelectListener{
            override fun onCalendarOutOfRange(calendar: Calendar?) {

            }

            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                calendar?.let {
                    mPresenter.requestData(it.timeInMillis)
                }
            }

        })
    }

    private fun initRecyclerView(){
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.calendar_rv_task)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        adapter.setEmptyView(R.layout.calendar_empty)
    }

    override fun getLayoutId(): Int = R.layout.calendar_fragment_layout


    override fun <T> showContent(data: T) {
        adapter.setList(data as MutableList<TaskEntity>)
        //adapter.setDiffNewData(data as MutableList<TaskEntity>)
    }

}