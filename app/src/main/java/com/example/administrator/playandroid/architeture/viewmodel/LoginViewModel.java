package com.example.administrator.playandroid.architeture.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.administrator.playandroid.architeture.repository.LoginRepository;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.UserInfo;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class LoginViewModel extends ViewModel {
    LiveData<Resource<UserInfo>> mLiveDataLogin;
    MutableLiveData<LoginRequest> mMutableLiveDataLogin=new MutableLiveData<>();
    @Inject
    public LoginViewModel(final LoginRepository pRepository) {
        mLiveDataLogin= Transformations.switchMap(mMutableLiveDataLogin, new Function<LoginRequest, LiveData<Resource<UserInfo>>>() {
            @Override
            public LiveData<Resource<UserInfo>> apply(LoginRequest input) {
                return pRepository.login(input.username,input.password);
            }
        });
    }

    public LiveData<Resource<UserInfo>> getLiveDataLogin() {
        return mLiveDataLogin;
    }
    public void login(String username,String pwd){
        mMutableLiveDataLogin.setValue(new LoginRequest(username,pwd));
    }
    class LoginRequest{
        public String username;
        public String password;

        public LoginRequest(String pUsername, String pPassword) {
            username = pUsername;
            password = pPassword;

        }
    }
}
