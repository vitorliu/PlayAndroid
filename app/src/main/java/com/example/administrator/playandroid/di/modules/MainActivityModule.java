package com.example.administrator.playandroid.di.modules;

import com.example.administrator.playandroid.di.annotation.FragmentScope;
import com.example.administrator.playandroid.ui.fragment.HomeFragment;
import com.example.administrator.playandroid.ui.fragment.KnowleageHierachyFragment;
import com.example.administrator.playandroid.ui.fragment.MineFragment;
import com.example.administrator.playandroid.ui.fragment.NavigationFragment;
import com.example.administrator.playandroid.ui.fragment.ProjectFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
@Module
public abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract KnowleageHierachyFragment knowleageHierachyFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract NavigationFragment navigationFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract ProjectFragment projectFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract MineFragment mineFragment();

}

