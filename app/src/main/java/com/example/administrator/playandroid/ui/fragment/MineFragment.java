package com.example.administrator.playandroid.ui.fragment;

import android.os.Bundle;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XFragment;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
public class MineFragment extends XFragment {
    @Inject
    public MineFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}