package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.HomeRepository;
import com.example.administrator.playandroid.base.bean.Resource;
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
public class HomeFragmentViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo<List<HomeBannerResponse>>>> mLiveDataHomeBanner;
    LiveData<Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>>> mLiveDataSeacherHotWord;
    LiveData<Resource<ResponseInfo<List<HomeCommonUseWebResponse>>>> mLiveDataHomeCommonUseWeb;
    LiveData<Resource<ResponseInfo<HomeArticleResponce>>> mLiveDataHomeArticleList;
    LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> mLiveDataHomeTopArticleList;

    MutableLiveData<Integer> mMutableLiveDataArticleList=new MutableLiveData<>();
    @Inject
    public HomeFragmentViewModel(final HomeRepository pRepository) {
        mLiveDataHomeBanner=pRepository.getHomeBannerData();
        mLiveDataSeacherHotWord=pRepository.getHomeSeacherHotWordData();
        mLiveDataHomeCommonUseWeb=pRepository.getHomeCommonUseWebData();
        mLiveDataHomeTopArticleList=pRepository.getHomeTopArticleListData();
        mLiveDataHomeArticleList= Transformations.switchMap(mMutableLiveDataArticleList, new Function<Integer, LiveData<Resource<ResponseInfo<HomeArticleResponce>>>>() {
            @Override
            public LiveData<Resource<ResponseInfo<HomeArticleResponce>>> apply(Integer input) {
                return pRepository.getHomeArticleListData(input);
            }
        });
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

    public LiveData<Resource<ResponseInfo<HomeArticleResponce>>> getLiveDataHomeArticleList() {
        return mLiveDataHomeArticleList;
    }

    public LiveData<Resource<ResponseInfo<List<HomeArticleListResponse>>>> getLiveDataHomeTopArticleList() {
        return mLiveDataHomeTopArticleList;
    }

    public void fetchArticleList(int pPage) {
        mMutableLiveDataArticleList.setValue(pPage);
    }
}
