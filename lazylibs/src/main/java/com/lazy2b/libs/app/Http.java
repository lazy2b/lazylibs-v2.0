package com.lazy2b.libs.app;

import okhttp3.OkHttpClient;

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
            httpClient = new OkHttpClient.Builder().build();
        }
        return httpClient;
    }

}
