package com.example.kotlintest.kotlintest.core

import android.app.Application

open class App : Application() {
    companion object {
        private var debug: Boolean = true
        fun getApplication() = Holder.INSTANCE
        open fun isDebug(): Boolean {
            return debug
        }
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