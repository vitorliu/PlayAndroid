package com.example.administrator.playandroid.di;

import android.app.Activity;

import com.example.administrator.playandroid.di.annotation.ActivityScope;
import com.example.administrator.playandroid.di.modules.MainActivityModule;
import com.example.administrator.playandroid.ui.activity.H5Activity;
import com.example.administrator.playandroid.ui.activity.LoginActivity;
import com.example.administrator.playandroid.ui.activity.MainActivity;
import com.example.administrator.playandroid.ui.activity.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector()
    abstract H5Activity h5Activity();

    @ActivityScope
    @ContributesAndroidInjector()
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector()
    abstract RegisterActivity registerActivity();
}
