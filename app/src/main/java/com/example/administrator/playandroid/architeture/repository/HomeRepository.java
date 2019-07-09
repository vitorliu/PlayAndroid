package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;
import com.example.administrator.playandroid.bean.HomeArticleResponce;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.HomeCommonUseWebResponse;
import com.example.administrator.playandroid.bean.HomeSeacherHotWordResponse;
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
    public LiveData<Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>>> getHomeSeacherHotWordData(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<HomeSeacherHotWordResponse>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<HomeSeacherHotWordResponse>>>> createCall() {
                return mApiService.requestHomeSeacherHotWord();
            }
        }.asLiveData();
    }
    public LiveData<Resource<ResponseInfo<List<HomeCommonUseWebResponse>>>> getHomeCommonUseWebData(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<HomeCommonUseWebResponse>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<HomeCommonUseWebResponse>>>> createCall() {
                return mApiService.requestHomeCommonUseWeb();
            }
        }.asLiveData();
    }
    public LiveData<Resource<ResponseInfo<HomeArticleResponce>>> getHomeArticleListData(final int page){
        return new NetworkBoundNoCacheResource<ResponseInfo<HomeArticleResponce>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<HomeArticleResponce>>> createCall() {
                return mApiService.requestHomeArticleList(page);
            }
        }.asLiveData();
    }
    public LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> getHomeTopArticleListData(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<HomeArticleListResponse>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<HomeArticleListResponse>>>> createCall() {
                return mApiService.requestHomeTopArticleList();
            }
        }.asLiveData();
    }
}
