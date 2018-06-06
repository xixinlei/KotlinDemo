package com.example.kotlintest.kotlintest

import android.widget.Button
import android.widget.TextView
import com.example.kotlintest.kotlintest.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun initListener() {
    }

    override fun initData() {
    }

    override fun initView() {
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
