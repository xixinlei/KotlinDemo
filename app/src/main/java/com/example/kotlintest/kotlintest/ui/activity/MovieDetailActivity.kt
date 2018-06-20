package com.example.kotlintest.kotlintest.ui.activity

import android.webkit.WebSettings
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.base.BaseActivity
import com.example.kotlintest.kotlintest.base.Constant
import kotlinx.android.synthetic.main.activity_movie_detail.*
import com.example.kotlintest.kotlintest.R.id.webView
import android.webkit.WebView
import android.webkit.WebViewClient


class MovieDetailActivity : BaseActivity() {
    private lateinit var url: String
    override fun initListener() {

    }

    override fun initData() {
        url = intent.getStringExtra(Constant.URL)
        val settings = webView.settings
        settings.setJavaScriptEnabled(true);  //支持js
        settings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        settings.setPluginState(WebSettings.PluginState.ON)//支持插件
        settings.setSupportZoom(true);  //支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        settings.setAllowFileAccess(true);  //设置可以访问文件
        settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        settings.setBuiltInZoomControls(true); //设置支持缩放
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(url)
    }

    override fun initView() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_movie_detail
    }
}
