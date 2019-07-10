package com.example.administrator.playandroid.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.bean.NavigationHeaderBean;
import com.example.administrator.playandroid.bean.NavigationResponce;
import com.example.administrator.playandroid.ui.activity.H5Activity;

import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class NavigatonAdapter extends BaseSectionQuickAdapter<NavigationHeaderBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public NavigatonAdapter(int layoutResId, int sectionHeadResId, List<NavigationHeaderBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, NavigationHeaderBean item) {
        helper.setText(R.id.tv_navigation_header,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationHeaderBean item) {
        final List<NavigationResponce.ArticlesBean> vArticles = item.t.articles;
        RecyclerView vRecyclerView=helper.getView(R.id.rv_navigation_list);
        NavigationChildAdapter vAdapter=new NavigationChildAdapter(R.layout.item_rv_navigation_child,vArticles);
        GridLayoutManager vGridLayoutManager=new GridLayoutManager(mContext,4);
        vRecyclerView.setLayoutManager(vGridLayoutManager);
        vRecyclerView.setAdapter(vAdapter);
        vAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NavigationResponce.ArticlesBean vArticlesBean = vArticles.get(position);
                H5Activity.launch(mContext,vArticlesBean.link);
            }
        });
    }
}
