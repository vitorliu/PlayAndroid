package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.ProjectRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ProjectClassifyResponce;
import com.example.administrator.playandroid.bean.ProjectListResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class ProjectViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo<List<ProjectClassifyResponce>>>>mLiveDataProjectClassify;
    LiveData<Resource<ResponseInfo<ProjectListResponce>>>mLiveDataProjectList;
    MutableLiveData<ProjectListRequest> mMutableLiveDataList=new MutableLiveData<>();
    @Inject
    public ProjectViewModel(final ProjectRepository pRepository) {
        mLiveDataProjectClassify=pRepository.getProjectClassifyListData();
        mLiveDataProjectList= Transformations.switchMap(mMutableLiveDataList, new Function<ProjectListRequest, LiveData<Resource<ResponseInfo<ProjectListResponce>>>>() {
            @Override
            public LiveData<Resource<ResponseInfo<ProjectListResponce>>> apply(ProjectListRequest input) {
                return pRepository.getProjectListData(input.page,input.cid);
            }
        });
    }

    public LiveData<Resource<ResponseInfo<List<ProjectClassifyResponce>>>> getLiveDataProjectClassify() {
        return mLiveDataProjectClassify;
    }

    public LiveData<Resource<ResponseInfo<ProjectListResponce>>> getLiveDataProjectList() {
        return mLiveDataProjectList;
    }

    public void fetchProjectListData(int page,int cid){
        mMutableLiveDataList.setValue(new ProjectListRequest(page,cid));
    }
    class ProjectListRequest{
        int page;
        int cid;

        public ProjectListRequest(int pPage, int pCid) {
            page = pPage;
            cid = pCid;
        }
    }
}
