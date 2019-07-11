package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundNoCacheResource;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

import retrofit2.http.Field;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class RegisterRepository {
    ApiService mApiService;
    @Inject
    public RegisterRepository(ApiService pApiService) {
        mApiService = pApiService;
    }

    public LiveData<Resource<ResponseInfo>> register(final String username, final String password, final String repassword){
        return new NetworkBoundNoCacheResource<ResponseInfo>() {
            @Override
            protected LiveData<ApiResponse<ResponseInfo>> createCall() {
                return mApiService.requestRegister(username,password,repassword);
            }
        }.asLiveData();
    }
}
