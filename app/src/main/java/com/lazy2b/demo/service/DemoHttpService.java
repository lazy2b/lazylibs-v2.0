package com.lazy2b.demo.service;

import com.lazy2b.demo.model.RespAppLineModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.demo.service
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public interface DemoHttpService<T> {
    //    app_line.js
    @GET("app_line.js")
    Call<T> loadData();
}