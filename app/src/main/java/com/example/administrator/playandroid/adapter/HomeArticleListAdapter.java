package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;

import java.util.List;

/**
 * Created by Administrator on 2019/7/4.
 * <p>Copyright 2019 Success101.</p>
 */
public class HomeArticleListAdapter extends BaseQuickAdapter<HomeArticleListResponse, BaseViewHolder> {
    public HomeArticleListAdapter(int layoutResId, @Nullable List<HomeArticleListResponse> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticleListResponse item) {
        helper.setText(R.id.item_article_title,item.title)
                .setText(R.id.item_article_author,item.author)
                .setText(R.id.item_article_classify,item.superChapterName)
                .setText(R.id.item_article_time,item.niceDate);
    }
}
