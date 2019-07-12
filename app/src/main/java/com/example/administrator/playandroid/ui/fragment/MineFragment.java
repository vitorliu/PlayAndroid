package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.bean.UserInfo;
import com.example.administrator.playandroid.ui.activity.CollectActivity;
import com.example.administrator.playandroid.ui.activity.LoginActivity;
import com.example.administrator.playandroid.ui.activity.SettingActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
public class MineFragment extends XFragment {
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @Inject
    LiveData<UserInfo>mUserInfoLiveData;
    @Inject
    public MineFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mUserInfoLiveData.observe(this, pUserInfo -> {
            if (pUserInfo!=null){
                tvHeader.setText(pUserInfo.nickname);
                tvHeader.setEnabled(false);
            }else {
                tvHeader.setText("点击登录");
                tvHeader.setEnabled(true);
            }
        });
    }

    @OnClick({R.id.tv_header, R.id.tv_collect, R.id.tv_todo,R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_header:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.tv_collect:
                startActivity(new Intent(getContext(), CollectActivity.class));
                break;
            case R.id.tv_todo:
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }
}
