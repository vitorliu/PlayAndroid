package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.ProjectClassifyResponce;
import com.example.administrator.playandroid.bean.ProjectListResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class ProjectRepository {
    ApiService mApiService;
    @Inject
    public ProjectRepository(ApiService pApiService) {
        mApiService = pApiService;
    }

    public LiveData<Resource<ResponseInfo<List<ProjectClassifyResponce>>>> getProjectClassifyListData(){
        return new NetworkBoundNoCacheResource<ResponseInfo<List<ProjectClassifyResponce>>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<List<ProjectClassifyResponce>>>> createCall() {
                return mApiService.requestProjectClassifyList();
            }
        }.asLiveData();
    }

    public LiveData<Resource<ResponseInfo<ProjectListResponce>>> getProjectListData(final int page, final int cid){
        return new NetworkBoundNoCacheResource<ResponseInfo<ProjectListResponce>>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo<ProjectListResponce>>> createCall() {
                return mApiService.requestProjectList(page,cid);
            }
        }.asLiveData();
    }
}
