package com.example.module_task.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.database.TaskEntity
import com.example.library_base.contract.IBaseContract
import com.example.library_base.view.BaseFragment
import com.example.library_common.router.RouterFragmentPath
import com.example.module_task.R
import com.example.module_task.adapter.TaskAdapter
import com.example.module_task.presenter.TaskCarePresenter

@Route(path = RouterFragmentPath.TASK)
class TaskCareFragment : BaseFragment<TaskCarePresenter>() {
    private val adapter = TaskAdapter(R.layout.task_care_item, null)
    private val recyclerView by lazy {
        rootView.findViewById<RecyclerView>(R.id.task_rv)
    }

    override val mPresenter: IBaseContract.IPresenter = TaskCarePresenter(this)

    override fun init() {
        initRecyclerView()
        initPresenter()
    }

    private fun initPresenter() {
        mPresenter.requestData()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        adapter.setEmptyView(R.layout.task_care_empty)
    }

    override fun getLayoutId(): Int = R.layout.task_fragment_layout


    override fun <T> showContent(data: T) {
        adapter.setDiffNewData(data as MutableList<TaskEntity>)
    }

}