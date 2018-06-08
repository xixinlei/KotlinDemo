package com.example.kotlintest.kotlintest

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.ui.NetworktestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")
    override fun initListener() {
        button.setOnClickListener {
            startActivity(Intent(MainActivity@ this, NetworktestActivity::class.java))
        }
    }

    override fun initData() {
    }

    override fun initView() {
        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除
        verifyStoragePermissions(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    /**
     * 申请权限
     *
     * @param activity
     */
    fun verifyStoragePermissions(activity: Activity) {

        try {
            //检测是否有写的权限
            val permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE")
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
