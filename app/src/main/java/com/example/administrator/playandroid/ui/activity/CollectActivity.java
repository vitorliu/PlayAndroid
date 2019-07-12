package com.example.administrator.playandroid.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.adapter.CollectArticleListAdapter;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.CollectViewModel;
import com.example.administrator.playandroid.base.XActivity;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.CollectArticleResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/7/12.
 * <p>Copyright 2019 Success101.</p>
 */
public class CollectActivity extends XActivity {
    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @Inject
    CollectViewModel mViewModel;

    List<CollectArticleResponse.DatasBean>mList;
    CollectArticleListAdapter mAdapter;
    int curPage;
    @Override
    public int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setupView();
        getCollectArticleListResult();
        getCancelCollectResult();
    }

    private void getCancelCollectResult() {
        mViewModel.getLiveDataCollectListUncollect().observe(this, pResponseInfoResource -> NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo>() {
            @Override
            public void onSuccess(ResponseInfo resource) {
                hideSimpleLoading();
                showToast("取消成功");
                refresh.autoRefresh();
            }

            @Override
            public void onLoading() {
                showSimpleLoading();
            }

            @Override
            public void onError(String errorMessage) {
                hideSimpleLoading();
                showToast(errorMessage);
            }
        }));
    }

    private void setupView() {
        mList=new ArrayList<>();
        mAdapter=new CollectArticleListAdapter(R.layout.item_collect_article_list,mList);
        rvCollect.setLayoutManager(new LinearLayoutManager(this));
        rvCollect.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> H5Activity.launch(CollectActivity.this,mList.get(position).link));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            CollectArticleResponse.DatasBean vDatasBean = mList.get(position);
            mViewModel.cancleCollect(vDatasBean.id,vDatasBean.originId);
        });

        refresh.setOnRefreshListener(refreshLayout -> {
            curPage=0;
            mViewModel.getCollectArticleData(curPage);
        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            mViewModel.getCollectArticleData(++curPage);
        });
        refresh.autoRefresh();
    }

    private void getCollectArticleListResult() {
        mViewModel.getLiveDataCollectArticleList().observe(this, pResponseInfoResource -> {
            refresh.finishLoadMore();
            refresh.finishRefresh();
            NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<CollectArticleResponse>>() {
                @Override
                public void onSuccess(ResponseInfo<CollectArticleResponse> resource) {
                    CollectArticleResponse vData = resource.data;
                    if (vData==null){
                        mAdapter.setEmptyView(emptyView);
                        return;
                    }
                    List<CollectArticleResponse.DatasBean> vDatas = vData.datas;
                    if (vDatas!=null){
                        if (curPage==0){
                            mList.clear();
                        }
                        mList.addAll(vDatas);
                    }
                    mAdapter.notifyDataSetChanged();
                    if (vData.over){
                        refresh.setEnableLoadMore(false);
                    }
                }

                @Override
                public void onLoading() {
                    mAdapter.setEmptyView(loadingView);
                }

                @Override
                public void onError(String errorMessage) {
                    mAdapter.setEmptyView(errorView);
                }
            });
        });
    }

    @Override
    protected boolean isShowStateView() {
        return true;
    }
}
