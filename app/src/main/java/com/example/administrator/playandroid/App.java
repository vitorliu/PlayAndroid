package com.example.administrator.playandroid;

import com.example.administrator.playandroid.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Administrator on 2019/6/27.
 * <p>Copyright 2019 Success101.</p>
 */
public class App extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
