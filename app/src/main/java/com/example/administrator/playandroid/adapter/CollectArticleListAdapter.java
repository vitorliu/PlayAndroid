package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.CollectArticleResponse;

import java.util.List;

/**
 * Created by Administrator on 2019/7/12.
 * <p>Copyright 2019 Success101.</p>
 */
public class CollectArticleListAdapter extends BaseQuickAdapter<CollectArticleResponse.DatasBean, BaseViewHolder> {
    public CollectArticleListAdapter(int layoutResId, @Nullable List<CollectArticleResponse.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectArticleResponse.DatasBean item) {
        helper.setText(R.id.item_article_title,item.title)
                .setText(R.id.item_article_author,item.author)
                .setText(R.id.item_article_classify,item.chapterName)
                .setText(R.id.item_article_time,item.niceDate)
                .addOnClickListener(R.id.tv_cancel);
    }
}
