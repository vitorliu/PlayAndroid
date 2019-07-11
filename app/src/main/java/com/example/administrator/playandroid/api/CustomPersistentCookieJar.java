package com.example.administrator.playandroid.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class CustomPersistentCookieJar extends PersistentCookieJar {
    Context context;

    public CustomPersistentCookieJar(Context context, CookieCache cache, CookiePersistor persistor) {
        super(cache, persistor);
        this.context = context;
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        super.saveFromResponse(url, cookies);
        synCookies(context, url.toString(), cookies);
   //     synCookies(context, ".wanandroid.com", cookies);

    }

    public void synCookies(Context context, String url, List<Cookie> cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.name();
            String cookieValue = cookie.value();
            if (!TextUtils.isEmpty(cookieName) && !TextUtils.isEmpty(cookieValue)) {
                sb.append(cookieName).append("=");
                sb.append(cookieValue).append(";");
            }
        }
        String[] cookie = sb.toString().split(";");
        for (String aCookie : cookie) {
            Log.d("cookie[i]", aCookie);
            cookieManager.setCookie(url, aCookie);// cookies是在HttpClient中获得的cookie
        }
        CookieSyncManager.getInstance().sync();
    }
}
