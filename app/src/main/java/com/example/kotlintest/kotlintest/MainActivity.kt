package com.example.kotlintest.kotlintest

import android.app.Activity
import android.app.FragmentManager
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlintest.kotlintest.adapter.TabFragmentAda
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.ui.fragment.*
import com.example.kotlintest.kotlintest.unit.navigationbar.NavigationTabBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity() {
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")
    override fun initListener() {
    }

    override fun initData() {
    }

    override fun initView() {
        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除
        verifyStoragePermissions(this)
        val colors = resources.getStringArray(R.array.default_preview)
        var models = ArrayList<NavigationTabBar.Model>()
        var titles: Array<String> = arrayOf("电影", "图书", "音乐", "广播", "相册")
        vp_horizontal_ntb.adapter = TabFragmentAda(supportFragmentManager)
        for (i in 0..titles.size - 1) {
            models.add(NavigationTabBar.Model.Builder(
                    getResources().getDrawable(R.mipmap.ic_second),
                    Color.parseColor(colors[i]))
                    .title(titles[i])
                    .badgeTitle("new")//角标
                    .build())
        }
        ntb_horizontal.models = models
        ntb_horizontal.setViewPager(vp_horizontal_ntb, 0)
        ntb_horizontal.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                ntb_horizontal.getModels().get(position).hideBadge()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        ntb_horizontal.postDelayed(Runnable {
            for (i in 0 until ntb_horizontal.models.size) {
                val model = ntb_horizontal.getModels().get(i)
                ntb_horizontal.postDelayed(Runnable { model.showBadge() }, 1000)////////////
            }
        }, 500)

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
