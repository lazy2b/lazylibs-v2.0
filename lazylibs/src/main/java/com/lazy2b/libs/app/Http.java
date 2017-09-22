package com.lazy2b.libs.app;

import com.lazy2b.libs.interfaces.IHttpHandler;
import com.lazy2b.libs.interfaces.IHttpService;
import com.lazy2b.libs.model.BaseReqCallBack;
import com.lazy2b.libs.retrofit.FastJsonConverterFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.libs.app
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public class Http {

    static OkHttpClient httpClient = null;

    public static final OkHttpClient inst() {
        if (httpClient == null) {
            httpClient = client(10);
        }
        return httpClient;
    }

    public static final OkHttpClient client(int timeOut) {
        return new OkHttpClient.Builder().connectTimeout(timeOut, TimeUnit.SECONDS).build();
    }

    static Retrofit refit = null;

    public static final Retrofit refit() {
        if (refit == null) {
            refit = new Retrofit.Builder()
                    .client(Http.inst())
                    .baseUrl("http://byyapp.oss-cn-shenzhen.aliyuncs.com/")
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return refit;
    }

    public static final <T> T service(Class<T> cls) {
        return refit().create(cls);
    }

    static IHttpService httpService = null;

    public static final IHttpService service(int timeOut) {
        if (timeOut == -1) {
            if (httpService == null) {
                httpService = refit().create(IHttpService.class);
            }
            return httpService;
        }
        return timeOut(timeOut);
    }

    public static final IHttpService timeOut(int timeOut) {
        return new Retrofit.Builder()
                .client(client(timeOut))
                .baseUrl("http://byyapp.oss-cn-shenzhen.aliyuncs.com/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(IHttpService.class);
    }

    static HashMap<String, Call> calls = new HashMap<>();

    public static final HashMap<String, Call> calls() {
        if (calls == null) {
            calls = new HashMap<>();
        }
        return calls;
    }

    public static final void remove(String action) {

    }

    public static final void cancel(String action) {
        if (calls == null || calls.size() == 0) return;
        if (calls.containsKey(action)) {
            if (calls.get(action).isExecuted()) {
                calls.get(action).cancel();
            }
            calls.remove(action);
        }
    }

    public synchronized static final void send(String action, String url, BaseReqCallBack callBack, Object bodyUnfixParms, int timeOut) {
        synchronized (calls) {
            calls();
            if (calls.containsKey(action)) {
                if (calls.get(action).isExecuted()) {
                    calls.get(action).cancel();
                }
                if (calls.get(action).isCanceled()) {
                    calls.get(action).cancel();
                }
                calls.remove(action);
            }
            if (bodyUnfixParms == null) {
                calls.put(action, service(timeOut).get(url));
            } else {
                calls.put(action, service(timeOut).post(url, bodyUnfixParms));
            }
            calls.get(action).enqueue(callBack);
        }
    }

    public static final void send(String action, String url, BaseReqCallBack callBack, int timeOut) {
        send(action, url, callBack, null, timeOut);
    }

    public static final void send(String action, String url, BaseReqCallBack callBack) {
        send(action, url, callBack, null, -1);
    }

    public static final void send(String action, String url, BaseReqCallBack callBack, Object bodyUnfixParms) {
        send(action, url, callBack, bodyUnfixParms, -1);
    }
}
