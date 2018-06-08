package com.example.kotlintest.kotlintest.api;

import com.example.kotlintest.kotlintest.entity.RetrofitEntity;
import com.example.kotlintest.kotlintest.entity.SubjectResulte;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 60959 on 2018/6/7.
 */

public class HttpApi extends BaseApi {
    private Map map = new HashMap();

    public HttpApi(RxAppCompatActivity rxAppCompatActivity, Map map, HttpOnNextListener listener) {
        super(listener, rxAppCompatActivity);
        this.map = map;
        setShowProgress(true);
        setCancel(true);
        setCache(false);
        setMethod("AppFiftyToneGraph/videoLink");
        setCookieNetWorkTime(60);
        setCookieNoNetWorkTime(24 * 60 * 60);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpService service = retrofit.create(HttpService.class);
        return service.getTestMap(map);
    }
}