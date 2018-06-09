package com.example.kotlintest.kotlintest.ui

import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.api.BaseResultEntity
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.api.UrlConstant
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.entity.MovieDetailEntity
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import kotlinx.android.synthetic.main.networktest.*

class NetworktestActivity : BaseActivity() {
    override fun initListener() {
        button.setOnClickListener {
            requestData()
        }

    }

    private fun requestData() {
        var map = HashMap<String, String>()
        map["id"] = "1764796";
        manager.doHttpDeal(HttpApi(
                this,
                map,
                UrlConstant.MOVIEDETAIL,
                object : HttpOnNextListener<MovieDetailEntity>() {
                    override fun onNext(subjects:MovieDetailEntity) {
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
