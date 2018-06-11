package com.example.kotlintest.kotlintest.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.adapter.MovieListAda
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.api.UrlConstant
import com.example.kotlintest.kotlintest.base.BaseFragment
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import kotlinx.android.synthetic.main.tabmoviefragment.*

class TabMovieFragment : BaseFragment() {
    private lateinit var mAdapter: MovieListAda
    private var start: Int = 0
    private var count: Int = 20

    override fun initData() {
        initAdapter()
        requestData()
    }

    private fun requestData() {
        var map = HashMap<String, String>()
        map["start"] = "$start"
        map["count"] = "$count"
        manager.doHttpDeal(HttpApi(
                rxcontext,
                map,
                UrlConstant.MOVIETOP250,
                object : HttpOnNextListener<MovieTop250Entity>() {
                    override fun onNext(data: MovieTop250Entity) {
                        if (start == 0) {
                            mAdapter.setNewData(data.subjects)
                        } else {
                            mAdapter.addData(data.subjects)
                            mAdapter.loadMoreComplete()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        super.onError(e)
                        Log.e("HTTP", e.toString())
                    }
                }
        ))

    }

    private fun initAdapter() {
        mAdapter = MovieListAda(R.layout.item_movie, null)
        movieList.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        movieList.setAdapter(mAdapter)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            start = mAdapter.data.size
            requestData()
        }, movieList)
    }

    override fun initView() {

    }

    override fun getLayout(): Int {
        return R.layout.tabmoviefragment
    }


}