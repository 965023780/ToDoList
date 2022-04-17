package com.example.library_network.util

import java.lang.StringBuilder

class UrlHttp {
    companion object{
        fun buildUrlWithParams(baseUrl: String, params: Map<String, String>): String{
            val url = StringBuilder("$baseUrl?")
            params.forEach{ (key, value) ->
                url.append("$key=$value&")
            }
            url.deleteCharAt(url.length - 1) //删除最后一位&
            return url.toString()
        }
    }
}