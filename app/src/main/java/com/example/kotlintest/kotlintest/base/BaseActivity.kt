package com.example.kotlintest.kotlintest.base

import android.app.Application
import android.os.Bundle
import android.view.View
import com.example.kotlintest.kotlintest.api.HttpManager
import com.example.kotlintest.kotlintest.core.App
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {
    lateinit var context: Application
    lateinit var manager: HttpManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = App.getApplication()
        manager = HttpManager.getInstance()
        setContentView(View.inflate(this, getLayoutId(), null))
        initView()
        initListener()
        initData()
    }

    abstract fun initListener()

    abstract fun initData()

    abstract fun initView()

    abstract fun getLayoutId(): Int
}