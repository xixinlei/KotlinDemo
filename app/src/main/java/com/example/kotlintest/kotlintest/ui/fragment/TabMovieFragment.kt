package com.example.kotlintest.kotlintest.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.R.id.movieList
import com.example.kotlintest.kotlintest.adapter.MovieListAda
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.api.UrlConstant
import com.example.kotlintest.kotlintest.base.BaseFragment
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import com.example.kotlintest.kotlintest.entity.SearchMovieEntity
import kotlinx.android.synthetic.main.tabmoviefragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import rx.functions.Action1


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
                            mAdapter.notifyDataSetChanged()
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
        searchView.setIconified(true)
        searchMovie()?.debounce(500, TimeUnit.MILLISECONDS)!!.observeOn(AndroidSchedulers.mainThread())!!.subscribe(Action1<String> { str ->
            var map = HashMap<String, String>()
            map["text"] = str.toString()
            manager.doHttpDeal(HttpApi(
                    rxcontext,
                    map,
                    UrlConstant.MOVIESEARCH,
                    object : HttpOnNextListener<SearchMovieEntity>() {
                        override fun onNext(data: SearchMovieEntity) {
                            Log.e("HTTP", data.toString())
                        }

                        override fun onError(e: Throwable?) {
                            super.onError(e)
                            Log.e("HTTP", e.toString())
                        }
                    }
            ))
        })
    }


    private fun searchMovie(): BehaviorSubject<String>? {
        val subject = BehaviorSubject.create("")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) subject.onNext(newText)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) subject.onNext(newText)
                return true
            }
        })
        return subject
    }

    override fun getLayout(): Int {
        return R.layout.tabmoviefragment
    }

}