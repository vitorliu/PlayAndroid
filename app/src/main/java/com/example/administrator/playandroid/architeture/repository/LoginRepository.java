package com.example.administrator.playandroid.architeture.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.administrator.playandroid.api.ApiService;
import com.example.administrator.playandroid.architeture.room.AppExecutors;
import com.example.administrator.playandroid.architeture.room.UserInfoDao;
import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.base.repository.NetworkBoundHasCacheResource;
import com.example.administrator.playandroid.bean.UserInfo;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class LoginRepository {
    ApiService mApiService;
    AppExecutors appExecutors;
    UserInfoDao userInfoDao;
    @Inject
    public LoginRepository(ApiService pApiService, AppExecutors pAppExecutors, UserInfoDao pUserInfoDao) {
        mApiService = pApiService;
        appExecutors = pAppExecutors;
        userInfoDao = pUserInfoDao;
    }

    public LiveData<Resource<UserInfo>> login(final String username, final String pwd){
        return new NetworkBoundHasCacheResource<UserInfo, ResponseInfo<UserInfo>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull ResponseInfo<UserInfo> item) {
                if (item.data!=null){
                    userInfoDao.insert(item.data);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable UserInfo data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<UserInfo> loadFromDb() {
                return userInfoDao.getUserInfo();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ResponseInfo<UserInfo>>> createCall() {
                return mApiService.requestLogin(username,pwd);
            }
        }.asLiveData();
    }
}
