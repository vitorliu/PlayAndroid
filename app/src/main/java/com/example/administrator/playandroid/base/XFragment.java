package com.example.administrator.playandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by Administrator on 2019/6/21.
 * <p>Copyright 2019 Success101.</p>
 */
public abstract class XFragment extends DaggerFragment implements UITemplate{
    View mRootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView==null){
            mRootView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this,mRootView);
        }else {
            ViewGroup vParent = (ViewGroup) mRootView.getParent();
            if (vParent!=null){
                vParent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }
}
