package com.example.module_user.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.library_base.contract.IBaseContract
import com.example.library_base.view.BaseFragment
import com.example.library_common.ui.RecyclerViewItemDecoration
import com.example.library_common.router.RouterFragmentPath
import com.example.library_common.util.DensityUtil
import com.example.module_user.R
import com.example.module_user.adapter.UserAdapter
import com.example.module_user.bean.UserSettingBean
import com.example.module_user.presenter.UserCarePresenter


@Route(path = RouterFragmentPath.USER)
class UserCareFragment : BaseFragment<UserCarePresenter>() {
    private val adapter by lazy {
        UserAdapter(requireContext(), R.layout.user_care_item_setting, null)
    }
    override val mPresenter: IBaseContract.IPresenter by lazy {
        UserCarePresenter(this)
    }


    override fun init() {
        initRecyclerView()
        initPresenter()
    }

    private fun initPresenter() {
        mPresenter.requestData()
    }


    private fun initRecyclerView() {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.user_rv)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        for (i in 0 until recyclerView.itemDecorationCount) {
            recyclerView.removeItemDecorationAt(i)
        }
        recyclerView.addItemDecoration(
            RecyclerViewItemDecoration(
                0,
                0,
                DensityUtil.dp2Px(requireContext(), 10F),
                DensityUtil.dp2Px(requireContext(), 15F)
            )
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        adapter.setEmptyView(R.layout.user_empty)
    }

    override fun getLayoutId(): Int = R.layout.user_care_fragment_layout

    @SuppressWarnings("unchecked")
    override fun <T> showContent(data: T) {
        adapter.setDiffNewData(data as MutableList<UserSettingBean>)
    }

}