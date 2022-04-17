package com.example.module_calender.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_calender.R

class CalendarActivity : AppCompatActivity() {

    //测试时候用的Activity
    //作为依赖的时候用Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity_layout)
    }

}