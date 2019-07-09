package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.HierachyClassifyResponce;
import com.example.administrator.playandroid.bean.HomeArticleResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/9.
 * <p>Copyright 2019 Success101.</p>
 */
public class HierachyRepository {
    ApiService mApiService;
    @Inject
    public HierachyRepository(ApiService pApiService) {
        mApiService = pApiService;
    }

    public LiveData<Resource<ResponseInfo<List<HierachyClassifyResponce>>>> getClassifyData(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<HierachyClassifyResponce>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<HierachyClassifyResponce>>>> createCall() {
                return mApiService.requestClassifyData();
            }
        }.asLiveData();
    }

    public  LiveData<Resource<ResponseInfo<HomeArticleResponce>>> getClassifyArticleList(final int page, final int cid){
        return new NetworkBoundNoCacheResource<ResponseInfo<HomeArticleResponce>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<HomeArticleResponce>>> createCall() {
                return mApiService.requestHierachyArticleList(page,cid);
            }
        }.asLiveData();
    }
}
