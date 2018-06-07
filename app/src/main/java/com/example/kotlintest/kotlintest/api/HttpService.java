package com.example.kotlintest.kotlintest.api;

import com.example.kotlintest.kotlintest.entity.RetrofitEntity;
import com.example.kotlintest.kotlintest.entity.SubjectResulte;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

interface HttpService {
    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEntity> getTestMap(@Field("once") Boolean once);
}
