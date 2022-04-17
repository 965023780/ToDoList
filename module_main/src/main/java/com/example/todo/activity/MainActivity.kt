package com.example.todo.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.database.DBManager
import com.example.database.TaskEntity
import com.example.todo.R
import com.example.todo.adapter.ContainerAdapter
import com.example.todo.adapter.ItemViewTouchHelper
import com.example.todo.application.App
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TASK = 0
        private const val CALENDAR = 1
        private const val STATS = 2
        private const val USER = 3
    }

    private val container by lazy {
        findViewById<ViewPager2>(R.id.main_container)
    }

    private val toolBar by lazy {
        findViewById<Toolbar>(R.id.main_toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (App.Theme.NORMAL == App.mTheme) {
            setContentView(R.layout.main_activity_layout)
        } else {
            setContentView(R.layout.main_care_activity_layout)
        }
        initView()
        //makeData()
    }

    @DelicateCoroutinesApi
    @TestOnly
    private fun makeData() {
        val list = arrayListOf("背英语单词", "体育锻炼三小时", "高强度写代码", "摸鱼二十五小时")
        val db = DBManager.getInstance(this)!!.getDaoSession(this)
        GlobalScope.launch {
            val limit = System.currentTimeMillis()
            for (i in 0 until 10000) {
                db.taskEntityDao.insert(
                    TaskEntity(
                        (limit - 604800000..limit).random(),
                        list.random(),
                        arrayListOf(true, false).random()
                    )
                )
            }
        }
    }

    private fun initView() {
        initFloatRootView()
        initToolBar()
        initContainer()
        initBottomView()
    }

    private fun initToolBar() {
        ImmersionBar.with(this)
            .statusBarColor(R.color.main_color_bar)
            .navigationBarColor(R.color.main_color_bar)
            .fitsSystemWindows(true)
            .autoDarkModeEnable(true)
            .init()
        toolBar.setBackgroundColor(ContextCompat.getColor(this, R.color.main_color_bar))
        toolBar.title = "任务"
    }

    private fun initFloatRootView() {
        val layoutParam = WindowManager.LayoutParams().apply {
            width = WRAP_CONTENT
            height = WRAP_CONTENT
            flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            this.x = container.width
            this.y = container.height
        }
        val floatRootView = if (App.Theme.NORMAL == App.mTheme) {
            LayoutInflater.from(this).inflate(R.layout.main_float_layout, null)
        } else {
            LayoutInflater.from(this).inflate(R.layout.main_care_float_layout, null)
        }
        floatRootView.setOnTouchListener(ItemViewTouchHelper(layoutParam, windowManager))
        floatRootView.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
        floatRootView.isFocusable = true
        floatRootView.contentDescription = "新增任务"
        windowManager.addView(floatRootView, layoutParam)
    }

    private fun initContainer() {
        container.adapter = ContainerAdapter(this)
        container.isUserInputEnabled = false

    }

    private fun initBottomView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_task -> {
                    container.currentItem = TASK
                    toolBar.title = "任务"
                    toolBar.contentDescription = "当前所在页面是 任务界面"
                    true
                }
                R.id.navigation_calendar -> {
                    container.currentItem = CALENDAR
                    toolBar.title = "日历"
                    toolBar.contentDescription = "当前所在页面是 日历界面"
                    true
                }
                R.id.navigation_stats -> {
                    container.currentItem = STATS
                    toolBar.title = "统计"
                    toolBar.contentDescription = "当前所在页面是 统计界面"
                    true
                }
                R.id.navigation_user -> {
                    container.currentItem = USER
                    toolBar.title = "个人信息"
                    toolBar.contentDescription = "当前所在页面是 个人信息界面"
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


}