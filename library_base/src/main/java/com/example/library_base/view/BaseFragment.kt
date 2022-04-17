package com.example.library_base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.library_base.contract.IBaseContract

abstract class BaseFragment<P>: Fragment(), IBaseContract.IView {
    protected lateinit var rootView: View

    abstract fun init()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!this::rootView.isInitialized) {
            rootView = LayoutInflater.from(context).inflate(getLayoutId(), container, false)
        }
        return rootView
    }

    abstract fun getLayoutId(): Int

    override fun onResume() {
        super.onResume()
        init()
    }

}