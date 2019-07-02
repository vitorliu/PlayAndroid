package com.example.administrator.playandroid.api;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by Administrator on 2019/7/2.
 * <p>Copyright 2019 Success101.</p>
 */
public interface ApiService {
    @GET("/banner/json")
    LiveData<ApiResponse<ResponseInfo<List<HomeBannerResponse>>>> requestHomeBanner();
}
