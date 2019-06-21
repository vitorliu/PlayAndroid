package com.example.administrator.commonlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/21.
 * <p>Copyright 2019 Success101.</p>
 */
public abstract class XActivity extends AppCompatActivity implements UITemplate{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId()>1){
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
        init(savedInstanceState);
    }
}
