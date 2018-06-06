package com.example.kotlintest.kotlintest.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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