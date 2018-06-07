package com.example.kotlintest.kotlintest.ui

import android.util.Log
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.base.BaseActivity
import kotlinx.android.synthetic.main.networktest.*

class NetworktestActivity : BaseActivity() {
    override fun initListener() {
        button.setOnClickListener {
            requestData()
        }

    }

    private fun requestData() {
        var map = HashMap<String, Boolean>()
        map["once_no"] = false
        manager.doHttpDeal(HttpApi(
                this,
                map,
                object : HttpOnNextListener<String>() {
                    override fun onNext(s: String) {
//                        textView.text = s
                        Log.e("TAG", s)
                    }

                    override fun onError(e: Throwable?) {
                        super.onError(e)
                        Log.e("TAG", e.toString())
                    }
                }
        ))
    }

    override fun initData() {

    }

    override fun initView() {
    }

    override fun getLayoutId(): Int {
        return R.layout.networktest
    }
}
