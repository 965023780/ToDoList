package com.example.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DBManager private constructor(mContext: Context) {
    private val DB_NAME = "tasks.db"
    private var mDevOpenlper: DaoMaster.DevOpenHelper? = null
    private var mDaomaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null

    init {
        mDevOpenlper = DaoMaster.DevOpenHelper(mContext, DB_NAME)
    }

    companion object {
        @Volatile
        var instance: DBManager? = null
        fun getInstance(mContext: Context): DBManager? {
            if (instance == null) {
                synchronized(DBManager::class) {
                    if (instance == null) {
                        instance = DBManager(mContext)
                    }
                }
            }
            return instance
        }
    }


    private fun getWriteableDataBase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenlper) {
            getInstance(context)
        }
        return mDevOpenlper?.writableDatabase
    }

    private fun getDaoMaster(context: Context): DaoMaster? {
        if (null == mDaomaster) {
            synchronized(DBManager::class) {
                if (null == mDaomaster) {
                    mDaomaster = DaoMaster(getWriteableDataBase(context))
                }
            }
        }
        return mDaomaster
    }

    fun getDaoSession(context: Context): DaoSession {
        if (null == mDaoSession) {
            synchronized(DBManager::class) {
                if (null == mDaoSession) {
                    mDaoSession = getDaoMaster(context)?.newSession()
                }
            }
        }
        return mDaoSession!!
    }

}