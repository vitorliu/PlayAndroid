package com.example.administrator.playandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.playandroid.R;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by Administrator on 2019/6/21.
 * <p>Copyright 2019 Success101.</p>
 */
public abstract class XFragment extends DaggerFragment implements UITemplate{
    View mRootView;

    protected View emptyView;
    protected View errorView;
    protected View loadingView;
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
        if (isShowStateView()){
            setupStateView();
        }
        init(savedInstanceState);
    }

    /**
     * 设置空view、异常view
     */
    protected void setupStateView() {
        loadingView=LayoutInflater.from(getContext()).inflate(R.layout.progress_loadingview_white,null,false);
        emptyView= LayoutInflater.from(getContext()).inflate(R.layout.progress_emptyview_white,null,false);
        errorView=LayoutInflater.from(getContext()).inflate(R.layout.progress_errorview_white,null,false);
        errorView.findViewById(R.id.progressCanClickView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClick();
            }
        });
    }
    /**
     * 错误页面点击事件
     */
    protected void onErrorViewClick(){

    }

    /**
     * 是否要显示状态view
     * @return
     */
    protected boolean isShowStateView(){
        return false;
    }
}
