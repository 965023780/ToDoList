package com.example.library_base.presenter

import com.example.library_base.contract.IBaseContract
import com.example.library_base.model.BaseModel
import java.lang.ref.Reference
import java.lang.ref.SoftReference

abstract class BasePresenter<V: IBaseContract.IView, M: BaseModel<out Any>>(view: V): IBaseContract.IPresenter{
    private val mViewRef: Reference<V> = SoftReference(view)
    protected lateinit var mModel: M
    init {
        initModel()
    }

    fun getView(): V? = if(mViewRef.get() == null) null else mViewRef.get()

}