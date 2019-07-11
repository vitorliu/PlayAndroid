package com.example.administrator.playandroid.di;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.administrator.playandroid.api.CustomPersistentCookieJar;
import com.example.administrator.playandroid.api.helper.ApiHelper;
import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.api.CustomTrustManager;
import com.example.administrator.playandroid.api.LiveDataCallAdapterFactory;
import com.example.administrator.playandroid.api.interceptor.PublicHeaderInterceptor;
import com.example.administrator.playandroid.api.interceptor.ReadCookiesInterceptor;
import com.example.administrator.playandroid.api.interceptor.SaveCookiesInterceptor;
import com.example.administrator.playandroid.architeture.room.AppDatabase;
import com.example.administrator.playandroid.architeture.room.UserInfoDao;
import com.example.administrator.playandroid.bean.UserInfo;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.BridgeInterceptor;
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

    @Provides
    @Singleton
    static PersistentCookieJar providePersistentCookieJar(Context application) {
        CustomPersistentCookieJar cookieJar = new CustomPersistentCookieJar(application, new SetCookieCache(), new SharedPrefsCookiePersistor(application));
        return cookieJar;
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(PersistentCookieJar cookieJar){
        OkHttpClient vOkHttpClient=new OkHttpClient
                .Builder()
                .connectTimeout(60l, TimeUnit.SECONDS)
                .readTimeout(60l,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.MINUTES)
                .cookieJar(cookieJar)
                .sslSocketFactory(ApiHelper.getSSLSocketFactory(),new CustomTrustManager())
                .addNetworkInterceptor(new PublicHeaderInterceptor())
                .addNetworkInterceptor(new SaveCookiesInterceptor())
                .addNetworkInterceptor(new BridgeInterceptor(cookieJar))
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

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(Context application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "playandroiddb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        return db;
    }

    @Provides
    @Singleton
    static UserInfoDao provideUserInfoDao(AppDatabase appDatabase) {
        UserInfoDao userInfoDao = appDatabase.userInfoDao();
        return userInfoDao;
    }

    @Provides
    @Singleton
    static LiveData<UserInfo> provideUserInfoLiveData(UserInfoDao userInfoDao) {
        return userInfoDao.getUserInfo();
    }
}
