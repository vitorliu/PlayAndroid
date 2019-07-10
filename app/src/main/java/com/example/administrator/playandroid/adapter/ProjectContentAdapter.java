package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.ProjectListResponce;

import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class ProjectContentAdapter extends BaseQuickAdapter<ProjectListResponce.DatasBean, BaseViewHolder> {
    public ProjectContentAdapter(int layoutResId, @Nullable List<ProjectListResponce.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListResponce.DatasBean item) {
        ImageView vImageView = helper.getView(R.id.iv_image);
        Glide.with(mContext).load(item.envelopePic).into(vImageView);
        helper.setText(R.id.tv_title,item.title)
                .setText(R.id.tv_desc,item.desc)
                .setText(R.id.tv_time,item.niceDate)
                .setText(R.id.tv_author,item.author);
    }
}
