package com.example.library_network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtil {
    companion object {

        fun getNetworkState(context: Context): NetworkState {
           return NetworkState.CELLULAR
        }
    }

    enum class NetworkState {
        UNAVAILABLE, //无网络连接
        WIFI, //WIFI网络连接
        CELLULAR, //移动网络连接
        OTHERS //其他网络连接
    }
}