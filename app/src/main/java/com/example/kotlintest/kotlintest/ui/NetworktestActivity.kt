package com.example.kotlintest.kotlintest.ui

import android.util.Log
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.R.id.button
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.entity.SubjectResulte
import kotlinx.android.synthetic.main.networktest.*

class NetworktestActivity : BaseActivity() {
    override fun initListener() {
        button.setOnClickListener {
            requestData()
        }

    }

    private fun requestData() {
        var map = HashMap<String, Boolean>()
        map["once"] = true
        manager.doHttpDeal(HttpApi(
                this,
                map,
                object : HttpOnNextListener<List<SubjectResulte>>() {
                    override fun onNext(subjects: List<SubjectResulte>) {
                        textView.text = subjects.toString()
                    }

                    override fun onError(e: Throwable?) {
                        super.onError(e)
                        textView.text = e.toString()
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
