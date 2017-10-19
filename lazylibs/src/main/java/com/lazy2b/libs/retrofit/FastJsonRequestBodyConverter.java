package com.lazy2b.libs.retrofit;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * 项目名: lazylibs-v2.0
 * 包 名: com.lazy2b.libs.retrofit
 * Copyright © 2017, LAZY2B.COM All Rights Reserved.
 * $Id$
 */

public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}