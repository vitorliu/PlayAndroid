package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.NavigationRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.NavigationResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class NavigationViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo<List<NavigationResponce>>>> mLiveDataNavigation;

    @Inject
    public NavigationViewModel(NavigationRepository pRepository) {
        mLiveDataNavigation=pRepository.getNavigationDataList();
    }

    public LiveData<Resource<ResponseInfo<List<NavigationResponce>>>> getLiveDataNavigation() {
        return mLiveDataNavigation;
    }
}
