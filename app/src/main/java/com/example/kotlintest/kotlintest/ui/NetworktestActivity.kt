package com.example.kotlintest.kotlintest.ui

import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import kotlinx.android.synthetic.main.networktest.*

class NetworktestActivity : BaseActivity() {
    override fun initListener() {
        button.setOnClickListener {
            requestData()
        }

    }

    private fun requestData() {
        var map = HashMap<String, Boolean>()
        manager.doHttpDeal(HttpApi(
                this,
                map,
                object : HttpOnNextListener<List<MovieTop250Entity>>() {
                    override fun onNext(subjects: List<MovieTop250Entity>) {
                        var str = ""
                        for (itm in subjects) {
                            str += itm.toString()
                        }
                        textView.text = str
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
