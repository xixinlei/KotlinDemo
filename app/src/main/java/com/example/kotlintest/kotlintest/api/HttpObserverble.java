package com.example.kotlintest.kotlintest.api;

import com.example.kotlintest.kotlintest.entity.MovieTop250Entity;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 60959 on 2018/6/8.
 * 生成请求所需observerble,强行抽取,以后再说
 */

public class HttpObserverble {

    public static Observable getObserverBle(Map map, int urlConstant, Retrofit retrofit) throws Exception {
        HttpService service = retrofit.create(HttpService.class);
        Observable observable = null;
        switch (urlConstant) {
            case UrlConstant.MOVIETOP250:
                observable = service.getTestMap(map);
                break;
        }
        if (observable == null) {
            throw new Exception("UrlConstantException");//没有校验,就是一把闪退
        }
        return observable;
    }
}
