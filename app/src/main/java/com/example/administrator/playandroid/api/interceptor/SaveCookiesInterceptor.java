package com.example.administrator.playandroid.api.interceptor;

import android.support.annotation.NonNull;


import com.example.administrator.playandroid.utils.SharedPreHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            SharedPreHelper.getSPHelper()
                    .putStringSet(SharedPreHelper.PREF_COOKIES, cookies);

        }
        return originalResponse;
    }
}