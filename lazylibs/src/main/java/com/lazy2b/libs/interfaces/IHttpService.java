package com.lazy2b.libs.interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.libs.interfaces
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public interface IHttpService {

    @GET("{url}")
    Call<String> get(@Path("url") String url);

    @POST("{url}")
    Call<String> post(@Path("url") String url, @Body Object body);

}
