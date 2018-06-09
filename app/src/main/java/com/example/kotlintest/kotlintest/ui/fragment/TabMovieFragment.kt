package com.example.kotlintest.kotlintest.ui.fragment

import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.api.UrlConstant
import com.example.kotlintest.kotlintest.base.BaseFragment
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import kotlinx.android.synthetic.main.tabmoviefragment.*

class TabMovieFragment : BaseFragment() {
    override fun initData() {
        button.setOnClickListener {
            //        var map = HashMap<String, String>()
//        map["id"] = "1764796";
//        manager.doHttpDeal(HttpApi(
//                this,
//                map,
//                UrlConstant.MOVIEDETAIL,
//                object : HttpOnNextListener<MovieDetailEntity>() {
//                    override fun onNext(subjects:MovieDetailEntity) {
//                        textView.text = subjects.toString()
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        super.onError(e)
//                        textView.text = e.toString()
//                    }
//                }
//        ))
            var map = HashMap<String, String>()
            manager.doHttpDeal(HttpApi(
                    rxcontext,
                    map,
                    UrlConstant.MOVIETOP250,
                    object : HttpOnNextListener<MovieTop250Entity>() {
                        override fun onNext(subjects: MovieTop250Entity) {
                            textView.text = subjects.toString()
                        }

                        override fun onError(e: Throwable?) {
                            super.onError(e)
                            textView.text = e.toString()
                        }
                    }
            ))
        }
    }

    override fun initListener() {
    }

    override fun getLayout(): Int {
        return R.layout.tabmoviefragment
    }


}