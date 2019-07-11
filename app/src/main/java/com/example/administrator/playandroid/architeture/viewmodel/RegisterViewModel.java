package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.RegisterRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class RegisterViewModel extends ViewModel {
    LiveData<Resource<ResponseInfo>>mLiveDataRegister;
    MutableLiveData<RegisterRequest> mMutableLiveDataRegister=new MutableLiveData<>();
    @Inject
    public RegisterViewModel(final RegisterRepository pRepository) {
        mLiveDataRegister= Transformations.switchMap(mMutableLiveDataRegister, new Function<RegisterRequest, LiveData<Resource<ResponseInfo>>>() {
            @Override
            public LiveData<Resource<ResponseInfo>> apply(RegisterRequest input) {
                return pRepository.register(input.username,input.password,input.repassword);
            }
        });
    }

    public LiveData<Resource<ResponseInfo>> getLiveDataRegister() {
        return mLiveDataRegister;
    }

    public void register(String user,String pwd){
        RegisterRequest vRequest=new RegisterRequest(user,pwd,pwd);
        mMutableLiveDataRegister.setValue(vRequest);
    }

    public class RegisterRequest {
        public String username;
        public String password;
        public String repassword;

        public RegisterRequest(String pUsername, String pPassword, String pRepassword) {
            username = pUsername;
            password = pPassword;
            repassword = pRepassword;
        }
    }
}
