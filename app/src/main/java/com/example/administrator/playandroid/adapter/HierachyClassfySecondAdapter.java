package com.example.administrator.playandroid.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.HierachyClassifyResponce;

import java.util.List;

/**
 * Created by Administrator on 2019/7/9.
 * <p>Copyright 2019 Success101.</p>
 */
public class HierachyClassfySecondAdapter extends BaseQuickAdapter<HierachyClassifyResponce.ChildrenBean, BaseViewHolder> {
    public HierachyClassfySecondAdapter(int layoutResId, @Nullable List<HierachyClassifyResponce.ChildrenBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HierachyClassifyResponce.ChildrenBean item) {
        helper.setText(R.id.item_name,item.name);
        TextView tv = helper.getView(R.id.item_name);
        if (item.status ) {
            tv.setTextColor(mContext.getResources().getColor(R.color.drop_down_selected));
            tv.setBackgroundResource(R.color.check_bg);
        } else {
            tv.setTextColor(mContext.getResources().getColor(R.color.drop_down_unselected));
            tv.setBackgroundResource(R.color.white);
        }
    }
}
