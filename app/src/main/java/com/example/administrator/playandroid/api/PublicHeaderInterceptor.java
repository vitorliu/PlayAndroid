package com.example.administrator.playandroid.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/7/2.
 * <p>Copyright 2019 Success101.</p>
 */
public class PublicHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request vRequest=chain.request().newBuilder()
                .build();
        return chain.proceed(vRequest);
    }
}
