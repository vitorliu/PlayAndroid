package com.example.administrator.playandroid.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.RegisterViewModel;
import com.example.administrator.playandroid.base.XActivity;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class RegisterActivity extends XActivity {
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Inject
    RegisterViewModel mViewModel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mViewModel.getLiveDataRegister().observe(this, new Observer<Resource<ResponseInfo>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo>() {
                    @Override
                    public void onSuccess(ResponseInfo resource) {
                        hideSimpleLoading();
                        showToast("注册成功");
                        finish();
                    }

                    @Override
                    public void onLoading() {
                        showSimpleLoading();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        hideSimpleLoading();
                        showToast(errorMessage);
                    }
                });
            }
        });
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String user = etUser.getText().toString();
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(user)){
            showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            showToast("请输入密码");
            return;
        }
        mViewModel.register(user,pwd);
    }
}
