package com.example.administrator.playandroid.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.room.AppExecutors;
import com.example.administrator.playandroid.architeture.room.UserInfoDao;
import com.example.administrator.playandroid.architeture.viewmodel.SettingViewModel;
import com.example.administrator.playandroid.base.XActivity;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ResponseInfo;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
public class SettingActivity extends XActivity {
    @Inject
    SettingViewModel mViewModel;
    @Inject
    UserInfoDao mUserInfoDao;
    @Inject
    AppExecutors mAppExecutors;
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_about_us, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_about_us:
                break;
            case R.id.tv_login_out:
                getLoginOurResult();
                break;
        }
    }

    private void getLoginOurResult() {
        mViewModel.getLiveDataLoginOut().observe(this, new Observer<Resource<ResponseInfo>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo>() {
                    @Override
                    public void onSuccess(ResponseInfo resource) {

                        mAppExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mUserInfoDao.delete();
                                mAppExecutors.mainThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        hideSimpleLoading();
                                        showToast("退出成功");
                                        finish();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onLoading() {
                        showSimpleLoading();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        hideSimpleLoading();
                    }
                });
            }
        });
    }
}
