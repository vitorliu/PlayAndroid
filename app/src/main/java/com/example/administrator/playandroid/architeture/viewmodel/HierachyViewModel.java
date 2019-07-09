package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.HierachyRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HierachyClassifyResponce;
import com.example.administrator.playandroid.bean.HomeArticleResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/9.
 * <p>Copyright 2019 Success101.</p>
 */
public class HierachyViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo<List<HierachyClassifyResponce>>>>mLiveDataClassify;
    LiveData<Resource<ResponseInfo<HomeArticleResponce>>>  mLiveDataArticle;
    MutableLiveData<ClassifyArticleRequest> mMutableLiveDataArticle=new MutableLiveData<>();
    @Inject
    public HierachyViewModel(final HierachyRepository pRepository) {
        mLiveDataClassify = pRepository.getClassifyData();
        mLiveDataArticle= Transformations.switchMap(mMutableLiveDataArticle, new Function<ClassifyArticleRequest, LiveData<Resource<ResponseInfo<HomeArticleResponce>>>>() {
            @Override
            public LiveData<Resource<ResponseInfo<HomeArticleResponce>>> apply(ClassifyArticleRequest input) {
                return pRepository.getClassifyArticleList(input.page,input.cid);
            }
        });
    }

    public LiveData<Resource<ResponseInfo<List<HierachyClassifyResponce>>>> getLiveDataClassify() {
        return mLiveDataClassify;
    }

    public LiveData<Resource<ResponseInfo<HomeArticleResponce>>> getLiveDataArticle() {
        return mLiveDataArticle;
    }

    public void fetchClassifyArticle(int page,int cid){
        mMutableLiveDataArticle.setValue(new ClassifyArticleRequest(page,cid));
    }
    class ClassifyArticleRequest{
        int page;
        int cid;

        public ClassifyArticleRequest(int pPage, int pCid) {
            page = pPage;
            cid = pCid;
        }
    }

}

