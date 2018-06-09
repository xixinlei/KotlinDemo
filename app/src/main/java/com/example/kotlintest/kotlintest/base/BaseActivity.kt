package com.example.kotlintest.kotlintest.base

import android.app.Application
import android.os.Bundle
import android.view.View
import com.example.kotlintest.kotlintest.api.HttpManager
import com.example.kotlintest.kotlintest.core.App
import com.example.kotlintest.kotlintest.unit.swipeact.SwipeBackActivityHelper
import com.example.kotlintest.kotlintest.unit.swipeact.SwipeBackLayout
import com.example.kotlintest.kotlintest.unit.swipeact.Utils
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {
    lateinit var context: Application

    lateinit var manager: HttpManager
    private var mHelper: SwipeBackActivityHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View.inflate(this, getLayoutId(), null))
        context = App.getApplication()
        manager = App.manager
        App.getApplication().setRxcontext(this)
        mHelper = SwipeBackActivityHelper(this)
        mHelper!!.onActivityCreate()
        initView()
        initListener()
        initData()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper!!.onPostCreate()
    }

    override fun <T : View?> findViewById(id: Int): T {
        val v = super.findViewById<View>(id)
        return if (v == null && mHelper != null) mHelper!!.findViewById(id) as T else v as T

    }

    fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper!!.getSwipeBackLayout()
    }

    fun setSwipeBackEnable(enable: Boolean) {
        getSwipeBackLayout().setEnableGesture(enable)
    }

    fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        getSwipeBackLayout().scrollToFinishActivity()
    }

    abstract fun initListener()

    abstract fun initData()

    abstract fun initView()


    abstract fun getLayoutId(): Int


}