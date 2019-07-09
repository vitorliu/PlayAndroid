package com.example.administrator.playandroid.di;

import android.arch.lifecycle.ViewModel;


import com.example.administrator.playandroid.architeture.viewmodel.HierachyViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.HomeFragmentViewModel;
import com.example.administrator.playandroid.di.annotation.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
@Module
public abstract class ViewModelBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel.class)
    abstract ViewModel bindHomeFragmentViewModel(HomeFragmentViewModel pViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HierachyViewModel.class)
    abstract ViewModel bindHierachyViewModel(HierachyViewModel pViewModel);
}
