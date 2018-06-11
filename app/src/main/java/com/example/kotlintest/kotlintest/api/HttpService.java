package com.example.kotlintest.kotlintest.api;

import com.example.kotlintest.kotlintest.entity.MovieDetailEntity;
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

interface HttpService {
    @GET("movie/top250")
    Observable<MovieTop250Entity> getMovieTop250(@QueryMap() HashMap<String,String> map);

    @GET("movie/subject/{id}")
    Observable<MovieDetailEntity> getMovieDetail(@Path("id") int id);
}
