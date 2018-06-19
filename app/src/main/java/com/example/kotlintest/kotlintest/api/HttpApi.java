package com.example.kotlintest.kotlintest.api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by 60959 on 2018/6/7.
 */

public class HttpApi extends BaseApi {
    private HashMap<String, String> map = new HashMap<String, String>();
    private int urlConstant;

    /**
     * 请求回调和参数,使用之前注意对参数进行校验
     *
     * @param rxAppCompatActivity 上下文
     * @param map                 请求参数
     * @param urlConstant         区分请求地址的tag
     * @param listener            回调
     */
    public HttpApi(RxAppCompatActivity rxAppCompatActivity, HashMap<String, String> map, int urlConstant, HttpOnNextListener listener) {
        super(listener, rxAppCompatActivity);
        this.map = map;
        this.urlConstant = urlConstant;
        setShowProgress(true);
        setCancel(false);
        setCache(false);
        setBaseUrl(HttpService.baseUrl);
//        setMethod("AppFiftyToneGraph/videoLink");
        setCookieNetWorkTime(60);
        setCookieNoNetWorkTime(24 * 60 * 60);
    }


    @Override
    public Observable getObservable(Retrofit retrofit) throws Exception {
        return HttpObserverble.getObserverBle(map, urlConstant, retrofit);
    }

}
