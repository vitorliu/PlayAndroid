package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.HomeRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;
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
public class HomeFragmentViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo<List<HomeBannerResponse>>>> mLiveDataHomeBanner;
    LiveData<Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>>> mLiveDataSeacherHotWord;
    LiveData<Resource<ResponseInfo<List<HomeCommonUseWebResponse>>>> mLiveDataHomeCommonUseWeb;
    LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> mLiveDataHomeArticleList;
    LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> mLiveDataHomeTopArticleList;
    @Inject
    public HomeFragmentViewModel(HomeRepository pRepository) {
        mLiveDataHomeBanner=pRepository.getHomeBannerData();
        mLiveDataSeacherHotWord=pRepository.getHomeSeacherHotWordData();
        mLiveDataHomeCommonUseWeb=pRepository.getHomeCommonUseWebData();
        mLiveDataHomeArticleList=pRepository.getHomeArticleListData();
        mLiveDataHomeTopArticleList=pRepository.getHomeTopArticleListData();
    }

    public LiveData<Resource<ResponseInfo<List<HomeBannerResponse>>>> getLiveDataHomeBanner() {
        return mLiveDataHomeBanner;
    }

    public LiveData<Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>>> getLiveDataSeacherHotWord() {
        return mLiveDataSeacherHotWord;
    }

    public LiveData<Resource<ResponseInfo<List<HomeCommonUseWebResponse>>>> getLiveDataHomeCommonUseWeb() {
        return mLiveDataHomeCommonUseWeb;
    }

    public LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> getLiveDataHomeArticleList() {
        return mLiveDataHomeArticleList;
    }

    public LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> getLiveDataHomeTopArticleList() {
        return mLiveDataHomeTopArticleList;
    }
}
