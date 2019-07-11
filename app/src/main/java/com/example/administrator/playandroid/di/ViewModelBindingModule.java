package com.example.administrator.playandroid.di;

import android.arch.lifecycle.ViewModel;


import com.example.administrator.playandroid.architeture.viewmodel.HierachyViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.HomeFragmentViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.LoginViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.NavigationViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.ProjectViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.RegisterViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.SettingViewModel;
import com.example.administrator.playandroid.di.annotation.ViewModelKey;
import com.example.administrator.playandroid.ui.activity.SettingActivity;

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

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel.class)
    abstract ViewModel bindNavigationViewModel(NavigationViewModel pViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProjectViewModel.class)
    abstract ViewModel bindProjectViewModel(ProjectViewModel pViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract ViewModel bindRegisterViewModel(RegisterViewModel pViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel pViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel.class)
    abstract ViewModel bindSettingViewModel(SettingViewModel pViewModel);
}
