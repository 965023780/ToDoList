package com.example.library_network.callback

interface Callback<T> {
    fun onStart()

    fun onFinished()

    fun onFail()

    fun onSuccess(result: T)
}