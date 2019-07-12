package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
                .setText(R.id.item_article_time,item.niceDate)
                .addOnClickListener(R.id.iv_collect);
        ImageView ivCollect = helper.getView(R.id.iv_collect);
        if (item.collect){
            ivCollect.setImageResource(R.drawable.has_collect);
        }else {
            ivCollect.setImageResource(R.drawable.no_collect);
        }
        TextView vView = helper.getView(R.id.item_top_flag);
        vView.setVisibility(item.type==1?View.VISIBLE:View.GONE);

    }
}
