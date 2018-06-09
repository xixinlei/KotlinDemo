package com.example.kotlintest.kotlintest.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlintest.kotlintest.api.HttpManager
import com.example.kotlintest.kotlintest.core.App
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

abstract class BaseFragment : Fragment() {
    lateinit var manager: HttpManager
    lateinit var rxcontext: RxAppCompatActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(getLayout(), container, false)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        manager = App.manager
        rxcontext = App.getApplication().getRxcpmtext()
        initListener()
        initData()
    }

    abstract fun initData()

    abstract fun initListener()

    abstract fun getLayout(): Int
}