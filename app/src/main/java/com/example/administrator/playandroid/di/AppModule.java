package com.example.administrator.playandroid.di;

import android.app.Application;
import android.content.Context;

import com.example.administrator.playandroid.api.ApiHelper;
import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.api.CustomTrustManager;
import com.example.administrator.playandroid.api.LiveDataCallAdapterFactory;
import com.example.administrator.playandroid.api.PublicHeaderInterceptor;
import com.google.gson.Gson;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
@Module(includes = {ViewModelBindingModule.class})
public abstract class AppModule {
    private static final String BASE_URL="https://www.wanandroid.com";

    @Binds
    abstract Context provideApplicationContext(Application pApplication);

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(){
        OkHttpClient vOkHttpClient=new OkHttpClient
                .Builder()
                .connectTimeout(60l, TimeUnit.SECONDS)
                .readTimeout(60l,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.MINUTES)
                .sslSocketFactory(ApiHelper.getSSLSocketFactory(),new CustomTrustManager())
                .addNetworkInterceptor(new PublicHeaderInterceptor())
                .build();
        return vOkHttpClient;
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient pClient){
        Retrofit vRetrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(pClient)
                .build();
        return vRetrofit;
    }

    @Singleton
    @Provides
    static ApiService provideApi(Retrofit pRetrofit){
        return pRetrofit.create(ApiService.class);
    }
}
