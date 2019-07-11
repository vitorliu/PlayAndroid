package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.SettingRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class SettingViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo>>mLiveDataLoginOut;
    @Inject
    public SettingViewModel(SettingRepository pRepository) {
        mLiveDataLoginOut=pRepository.loginOut();
    }

    public LiveData<Resource<ResponseInfo>> getLiveDataLoginOut() {
        return mLiveDataLoginOut;
    }
}
