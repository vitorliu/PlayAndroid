package com.example.administrator.playandroid.ui.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
    }
}
