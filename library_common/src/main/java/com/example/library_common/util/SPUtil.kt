package com.example.library_common.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.library_common.constant.Constants

class SPUtil private constructor() {

    companion object {
        val instance: SPUtil by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            SPUtil()
        }
    }

    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    @SuppressLint("CommitPrefEdits")
    fun init(context: Context) {
        sp = context.getSharedPreferences(
            Constants.Sp.SHARED_PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
        editor = sp!!.edit()
    }

    fun saveInt(key: String, values: Int) {
        editor!!.putInt(key, values).apply()
    }


    fun saveLong(key: String, values: Long) {
        editor!!.putLong(key, values).apply()
    }


    fun saveBoolean(key: String, values: Boolean) {
        editor!!.putBoolean(key, values).apply()
    }

    fun saveString(key: String, values: String) {
        editor!!.putString(key, values)
    }

    fun getBoolean(key: String): Boolean =
        sp!!.getBoolean(key, Constants.Sp.DEFAULT_BOOLEAN)

    fun getLong(key: String): Long = sp!!.getLong(key, Constants.Sp.DEFAULT_LONG)

    fun getInt(key: String): Int = sp!!.getInt(key, Constants.Sp.DEFAULT_INT)

    fun getString(key: String): String = sp!!.getString(key, Constants.Sp.DEFAULT_STRING)!!
}