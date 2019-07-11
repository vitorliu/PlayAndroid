package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class SettingRepository {
    ApiService mApiService;
    @Inject
    public SettingRepository(ApiService pApiService) {
        mApiService = pApiService;
    }
    public LiveData<Resource<ResponseInfo>>loginOut(){
        return new NetworkBoundNoCacheResource<ResponseInfo>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo>> createCall() {
                return mApiService.requestLoginOut();
            }
        }.asLiveData();
    }
}
