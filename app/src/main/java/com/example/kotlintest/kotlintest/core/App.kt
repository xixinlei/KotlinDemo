package com.example.kotlintest.kotlintest.core

import android.app.Application
import com.example.kotlintest.kotlintest.api.HttpManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

open class App : Application() {
    private lateinit var rxcontext: RxAppCompatActivity

    companion object {
        var manager: HttpManager = HttpManager.getInstance()
        private var debug: Boolean = true
        fun getApplication() = Holder.INSTANCE
        open fun isDebug(): Boolean {
            return debug
        }
    }

    fun setRxcontext(rxcontext: RxAppCompatActivity) {
        this.rxcontext = rxcontext
    }

    fun getRxcpmtext(): RxAppCompatActivity {
        return rxcontext
    }


    private var debug: Boolean = true
    override fun onCreate() {
        super.onCreate()
        setDebug(true)
    }

    private object Holder {
        val INSTANCE = App()
    }

    fun setDebug(debug: Boolean) {
        Holder.INSTANCE.debug = debug
    }
}