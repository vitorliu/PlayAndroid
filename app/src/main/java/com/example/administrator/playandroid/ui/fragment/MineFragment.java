package com.example.administrator.playandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.ui.activity.LoginActivity;

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
    public MineFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_header, R.id.tv_collect, R.id.tv_todo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_header:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.tv_collect:
                break;
            case R.id.tv_todo:
                break;
        }
    }
}
