package com.example.kotlintest.kotlintest.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.R.id.movieList
import com.example.kotlintest.kotlintest.adapter.MovieListAda
import com.example.kotlintest.kotlintest.api.HttpApi
import com.example.kotlintest.kotlintest.api.HttpOnNextListener
import com.example.kotlintest.kotlintest.api.UrlConstant
import com.example.kotlintest.kotlintest.base.BaseFragment
import com.example.kotlintest.kotlintest.base.Constant
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import com.example.kotlintest.kotlintest.entity.SearchMovieEntity
import com.example.kotlintest.kotlintest.ui.activity.MovieDetailActivity
import kotlinx.android.synthetic.main.tabmoviefragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import rx.functions.Action1


class TabMovieFragment : BaseFragment() {
    private lateinit var mAdapter: MovieListAda
    private var start: Int = 0
    private var count: Int = 20
    private var listSign: listType = listType.TOP250
    private var editText: EditText? = null

    enum class listType { //列表类型
        TOP250,
        SEARCH
    }

    override fun initData() {
        initAdapter()
        requestData("")//请求top250数据
    }

    private fun requestData(str: String) {
        var map = HashMap<String, String>()
        when (listSign) {
            listType.TOP250 -> {
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
                                movieList.scrollToPosition(0)//请求之后滚动到第一个
                            }

                            override fun onError(e: Throwable?) {
                                super.onError(e)
                                mAdapter.loadMoreEnd()
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT)
                            }
                        }
                ))
            }
            listType.SEARCH -> {
                map["text"] = str
                manager.doHttpDeal(HttpApi(
                        rxcontext,
                        map,
                        UrlConstant.MOVIESEARCH,
                        object : HttpOnNextListener<SearchMovieEntity>() {
                            override fun onNext(data: SearchMovieEntity) {
                                mAdapter.setNewData(data.subjects)
                                start = 0
                                movieList.scrollToPosition(0)//请求之后滚动到第一个
                            }

                            override fun onError(e: Throwable?) {
                                super.onError(e)
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT)
                            }
                        }
                ))
            }
        }

    }

    private fun initAdapter() {
        mAdapter = MovieListAda(R.layout.item_movie, null)
        movieList.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        movieList.setAdapter(mAdapter)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (listSign === listType.TOP250) {
                start = mAdapter.data.size
                requestData("")
            } else {
                mAdapter.loadMoreEnd()
            }
        }, movieList)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val any = mAdapter.data[position]
            startActivity(Intent(context, MovieDetailActivity()::class.java).putExtra(Constant.URL, any.alt))
        }
    }

    override fun initView() {
        searchView.setIconified(true)
        editText = searchView.findViewById<EditText>(searchView.context.resources.getIdentifier("android:id/search_src_text", null, null))
        editText!!.hint = "电影搜索"
        searchMovie()?.debounce(500, TimeUnit.MILLISECONDS)!!.observeOn(AndroidSchedulers.mainThread())!!.subscribe(Action1<String> { str ->
            listSign = listType.SEARCH
            start = 0
            requestData(str)
        })
        searchView.setOnQueryTextFocusChangeListener(View.OnFocusChangeListener { view, b ->
            if (!b) {
                listSign = listType.TOP250
                start = 0
                requestData("")
            }
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