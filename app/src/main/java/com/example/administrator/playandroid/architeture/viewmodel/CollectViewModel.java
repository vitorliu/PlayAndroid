package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.CollectRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.CollectArticleResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/12.
 * <p>Copyright 2019 Success101.</p>
 */
public class CollectViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo>>mLiveDataCollect;
    LiveData<Resource<ResponseInfo>>mLiveDataUncollect;
    LiveData<Resource<ResponseInfo>>mLiveDataCollectListUncollect;
    LiveData<Resource<ResponseInfo<CollectArticleResponse>>>mLiveDataCollectArticleList;
    MutableLiveData<Integer> mMutableCollect=new MutableLiveData<>();
    MutableLiveData<Integer> mMutableUnollect=new MutableLiveData<>();
    MutableLiveData<UnCollectRequest> mMutableCollectListUnollect=new MutableLiveData<>();
    MutableLiveData<Integer>mMutableCollectArticleList=new MutableLiveData<>();
    @Inject
    public CollectViewModel(CollectRepository pRepository) {
        mLiveDataCollect= Transformations.switchMap(mMutableCollect, input -> pRepository.getCollectData(input));
        mLiveDataUncollect= Transformations.switchMap(mMutableUnollect, input -> pRepository.getUncollectData(input));
        mLiveDataCollectArticleList=Transformations.switchMap(mMutableCollectArticleList, input -> pRepository.getCollectArticleData(input));
        mLiveDataCollectListUncollect=Transformations.switchMap(mMutableCollectListUnollect, input -> pRepository.getCollectListUncollectData(input.id,input.originId));
    }

    public LiveData<Resource<ResponseInfo>> getLiveDataCollect() {
        return mLiveDataCollect;
    }

    public LiveData<Resource<ResponseInfo>> getLiveDataUncollect() {
        return mLiveDataUncollect;
    }

    public LiveData<Resource<ResponseInfo<CollectArticleResponse>>> getLiveDataCollectArticleList() {
        return mLiveDataCollectArticleList;
    }

    public LiveData<Resource<ResponseInfo>> getLiveDataCollectListUncollect() {
        return mLiveDataCollectListUncollect;
    }

    public void collect(int id){
        mMutableCollect.setValue(id);
    }
    public void unCollect(int id){
        mMutableUnollect.setValue(id);
    }

    public void getCollectArticleData(int page){
        mMutableCollectArticleList.setValue(page);
    }

    public void cancleCollect(int id,int originId){
        mMutableCollectListUnollect.setValue(new UnCollectRequest(id,originId));
    }
    class UnCollectRequest{
        int id;
        int originId;

        public UnCollectRequest(int pId, int pOriginId) {
            id = pId;
            originId = pOriginId;
        }
    }
}
