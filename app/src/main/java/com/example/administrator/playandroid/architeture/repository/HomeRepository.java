package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/2.
 * <p>Copyright 2019 Success101.</p>
 */
public class HomeRepository {
    ApiService mApiService;
    @Inject
    public HomeRepository(ApiService pApiService) {
        mApiService = pApiService;
    }

    public LiveData<Resource<ResponseInfo<List<HomeBannerResponse>>>> getHomeBannerData(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<HomeBannerResponse>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<HomeBannerResponse>>>> createCall() {
                return mApiService.requestHomeBanner();
            }
        }.asLiveData();
    }
}
