package com.example.kotlintest.kotlintest.api

/**
 * Created by 60959 on 2018/6/8.
 * url常量用来区分请求地址,lowb方法
 */
class UrlConstant {
    companion object {
        const val MOVIETOP250: Int = 1;//豆瓣电影top250
        const val MOVIEDETAIL: Int = 2;//豆瓣电影详情
        const val MOVIESEARCH: Int = 3;//电影搜索
    }
}