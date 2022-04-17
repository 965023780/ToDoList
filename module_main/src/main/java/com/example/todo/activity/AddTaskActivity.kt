package com.example.todo.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.database.DBManager
import com.example.database.TaskEntity
import com.example.todo.R
import com.example.todo.application.App
import com.gyf.immersionbar.ImmersionBar


class AddTaskActivity : AppCompatActivity() {

    private val toolBar by lazy {
        findViewById<Toolbar>(R.id.main_toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (App.Theme.NORMAL == App.mTheme) {
            setContentView(R.layout.main_activty_add_layout)
        } else {
            setContentView(R.layout.main_care_activty_add_layout)
        }
        init()
    }


    private fun init() {
        initToolBar()
        initSubmitButton()
    }

    private fun initToolBar() {
        ImmersionBar.with(this)
            .statusBarColor(R.color.main_color_bar)
            .navigationBarColor(R.color.main_color_bar)
            .fitsSystemWindows(true)
            .autoDarkModeEnable(true)
            .init()
        toolBar.setBackgroundColor(ContextCompat.getColor(this, R.color.main_color_bar))
        toolBar.title = "添加任务"
    }

    private fun initSubmitButton() {
        val button = findViewById<AppCompatButton>(R.id.main_btn_submit)
        button.setOnClickListener {
            val editView = findViewById<AppCompatEditText>(R.id.main_edit_task)
            DBManager.getInstance(this)!!.getDaoSession(this).taskEntityDao.insert(
                TaskEntity(
                    System.currentTimeMillis(),
                    editView.text.toString(),
                    false
                )
            )
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}