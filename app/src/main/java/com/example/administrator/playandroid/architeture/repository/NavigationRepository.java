package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.NavigationResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class NavigationRepository {
    ApiService mApiService;
    @Inject
    public NavigationRepository(ApiService pApiService) {
        mApiService = pApiService;
    }

    public LiveData<Resource<ResponseInfo<List<NavigationResponce>>>> getNavigationDataList(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<NavigationResponce>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<NavigationResponce>>>> createCall() {
                return mApiService.requestNavigationDataList();
            }
        }.asLiveData();
    }
}
