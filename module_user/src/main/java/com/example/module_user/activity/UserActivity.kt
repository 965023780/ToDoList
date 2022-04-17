package com.example.module_user.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_user.R

//作为测试的时候使用
class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_layout)
    }
}