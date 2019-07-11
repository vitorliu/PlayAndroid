package com.example.administrator.playandroid.api.interceptor;

import android.support.annotation.NonNull;


import com.example.administrator.playandroid.utils.SharedPreHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReadCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences =  SharedPreHelper.getSPHelper().getStringSet(SharedPreHelper.PREF_COOKIES);
        if (!preferences.isEmpty()) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
            return chain.proceed(builder.build());
        }
        return null;
    }
}