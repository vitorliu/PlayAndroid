package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.NavigationResponce;

import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class NavigationChildAdapter extends BaseQuickAdapter<NavigationResponce.ArticlesBean, BaseViewHolder> {
    public NavigationChildAdapter(int layoutResId, @Nullable List<NavigationResponce.ArticlesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationResponce.ArticlesBean item) {
        helper.setText(R.id.tv_child,item.title);
    }
}
