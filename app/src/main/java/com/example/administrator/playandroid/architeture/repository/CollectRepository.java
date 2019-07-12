package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.CollectArticleResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/12.
 * <p>Copyright 2019 Success101.</p>
 */
public class CollectRepository {
    ApiService mApiService;
    @Inject
    public CollectRepository(ApiService pApiService) {
        mApiService = pApiService;
    }

    public LiveData<Resource<ResponseInfo>>getCollectData(int id){
        return new NetworkBoundNoCacheResource<ResponseInfo>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo>> createCall() {
                return mApiService.requestCollect(id);
            }
        }.asLiveData();
    }

    public LiveData<Resource<ResponseInfo>>getUncollectData(int id){
        return new NetworkBoundNoCacheResource<ResponseInfo>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo>> createCall() {
                return mApiService.requestUncollect(id);
            }
        }.asLiveData();
    }

    public LiveData<Resource<ResponseInfo<CollectArticleResponse>>>getCollectArticleData(int page){
        return new NetworkBoundNoCacheResource<ResponseInfo<CollectArticleResponse>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<CollectArticleResponse>>> createCall() {
                return mApiService.requestCollectArticle(page);
            }
        }.asLiveData();
    }

    public LiveData<Resource<ResponseInfo>>getCollectListUncollectData(int id,int originId){
        return new NetworkBoundNoCacheResource<ResponseInfo>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo>> createCall() {
                return mApiService.requestCollectListUncollect(id,originId);
            }
        }.asLiveData();
    }
}
