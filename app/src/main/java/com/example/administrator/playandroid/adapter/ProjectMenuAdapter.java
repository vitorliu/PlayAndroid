package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.ProjectClassifyResponce;

import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class ProjectMenuAdapter extends BaseQuickAdapter<ProjectClassifyResponce, BaseViewHolder> {
    public ProjectMenuAdapter(int layoutResId, @Nullable List<ProjectClassifyResponce> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectClassifyResponce item) {
        TextView tv = helper.getView(R.id.tv_menu);
        if (item.isCheck ) {
            tv.setTextColor(mContext.getResources().getColor(R.color.drop_down_selected));
            tv.setBackgroundResource(R.color.check_bg);
        } else {
            tv.setTextColor(mContext.getResources().getColor(R.color.drop_down_unselected));
            tv.setBackgroundResource(R.color.white);
        }
        helper.setText(R.id.tv_menu,item.name);
    }
}
