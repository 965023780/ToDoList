package com.example.library_network.bean

data class ApiResult<T>(val code: Int, val msg: String, val data: T) {
    companion object{
        const val SUCCESS_CODE = 200
    }

    fun isSuccess() = code == SUCCESS_CODE

    override fun toString() = "ApiResult: {\ncode = $code\nmsg = $msg\ndata = $data\n}"
}