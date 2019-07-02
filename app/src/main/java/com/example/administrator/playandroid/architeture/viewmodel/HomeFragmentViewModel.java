package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.HomeRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/2.
 * <p>Copyright 2019 Success101.</p>
 */
public class HomeFragmentViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo<List<HomeBannerResponse>>>> mLiveDataHomeBanner;
    @Inject
    public HomeFragmentViewModel(HomeRepository pRepository) {
        mLiveDataHomeBanner=pRepository.getHomeBannerData();
    }

    public LiveData<Resource<ResponseInfo<List<HomeBannerResponse>>>> getLiveDataHomeBanner() {
        return mLiveDataHomeBanner;
    }
}
