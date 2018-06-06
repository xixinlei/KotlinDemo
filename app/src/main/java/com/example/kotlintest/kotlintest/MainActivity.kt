package com.example.kotlintest.kotlintest

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onclick()
    }

    fun onclick() {
        val value = Module("Jane", 11)
        val arr: IntArray = intArrayOf(1, 2, 3, 4, 5)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        button.setOnClickListener(View.OnClickListener {

        })
    }
}
