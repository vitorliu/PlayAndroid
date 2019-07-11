package com.example.administrator.playandroid.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.LoginViewModel;
import com.example.administrator.playandroid.base.XActivity;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.UserInfo;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class LoginActivity extends XActivity {
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @Inject
    LoginViewModel mViewModel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
      mViewModel.getLiveDataLogin().observe(this, new Observer<Resource<UserInfo>>() {
          @Override
          public void onChanged(@Nullable Resource<UserInfo> pUserInfoResource) {
              showToast("登录成功");
              finish();
          }
      });
    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
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
                mViewModel.login(user,pwd);
                break;
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
