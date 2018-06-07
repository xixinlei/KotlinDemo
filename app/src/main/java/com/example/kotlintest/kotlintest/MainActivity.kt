package com.example.kotlintest.kotlintest

import android.content.Intent
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.ui.NetworktestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initListener() {
        button.setOnClickListener {
            startActivity(Intent(this, NetworktestActivity::class.java))
        }
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
