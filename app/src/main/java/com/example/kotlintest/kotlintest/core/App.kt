package com.example.kotlintest.kotlintest.core

import android.app.Application

open class App : Application {
    companion object {
        private lateinit var app: App
        private var debug: Boolean = true
        open fun getApplication(): App {
            return app
        }

        open fun isDebug(): Boolean {
            return debug
        }
    }

    private var debug: Boolean = true
    override fun onCreate() {
        super.onCreate()
        setDebug(true)
    }

    fun setDebug(debug: Boolean) {
        app.debug = debug
    }

    private constructor()

}