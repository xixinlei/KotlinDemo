package com.example.kotlintest.kotlintest.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by 60959 on 2018/6/7.
 * 接口相关
 */
interface HttpService {
    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink/")
    abstract fun getTestMap(@Field("map") map: Map<*, *>): Observable<BaseResultEntity<List<HttpApi>>>
}