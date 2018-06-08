package com.example.kotlintest.kotlintest.api;

import com.example.kotlintest.kotlintest.entity.MovieTop250Entity;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

interface HttpService {
    @FormUrlEncoded
    @POST("movie/top250")
    Observable<BaseResultEntity<List<MovieTop250Entity>>> getTestMap(@Field("") Map map);
}
