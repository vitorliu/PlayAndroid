package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.adapter.HierachyClassfyFirstAdapter;
import com.example.administrator.playandroid.adapter.HierachyClassfySecondAdapter;
import com.example.administrator.playandroid.adapter.HomeArticleListAdapter;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.HierachyViewModel;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HierachyClassifyResponce;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;
import com.example.administrator.playandroid.bean.HomeArticleResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;
import com.example.administrator.playandroid.ui.view.DropDownMenu;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
public class KnowleageHierachyFragment extends XFragment {
    @BindView(R.id.hierachy_drop_dwon_menu)
    DropDownMenu hierachyDropDwonMenu;
    @Inject
    HierachyViewModel mViewModel;
    List<View>popuviews;
    List<String>headerList;

    RecyclerView rvFirst;
    HierachyClassfyFirstAdapter firstAdapter;
    List<HierachyClassifyResponce>firstList;

    RecyclerView rvSecond;
    HierachyClassfySecondAdapter secondAdapter;
    List<HierachyClassifyResponce.ChildrenBean> secondList;
    @Inject
    public KnowleageHierachyFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragmet_knowleage_hierachy;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        headerList=new ArrayList<>(1);
        headerList.add("选择知识体系类别");

        popuviews=new ArrayList<>(1);
        View menuView = getMenuView();
        popuviews.add(menuView);

        View contentView = getContentView();

        hierachyDropDwonMenu.setDropDownMenu(headerList,popuviews,contentView);

        getClassifyResult();
        getClassifyArticleList();
    }

    @Override
    protected boolean isShowStateView() {
        return true;
    }

    private void getClassifyArticleList() {
        mViewModel.getLiveDataArticle().observe(this, new Observer<Resource<ResponseInfo<HomeArticleResponce>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<HomeArticleResponce>> pResponseInfoResource) {
                mRefreshLayout.finishRefresh();
                mRefreshLayout.finishLoadMore();
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<HomeArticleResponce>>() {
                    @Override
                    public void onSuccess(ResponseInfo<HomeArticleResponce> resource) {
                        HomeArticleResponce vData = resource.data;
                        if (vData==null){
                            mArticleListAdapter.setEmptyView(emptyView);
                            return;
                        }
                        List<HomeArticleListResponse> vDatas = vData.datas;
                        if (vDatas!=null){
                            if (curPage==0){
                                mArticleList.clear();
                            }
                            mArticleList.addAll(vDatas);
                        }
                        mArticleListAdapter.notifyDataSetChanged();
                        if (vData.over){
                            mRefreshLayout.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    public void onLoading() {
                        mArticleListAdapter.setEmptyView(loadingView);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        mArticleListAdapter.setEmptyView(errorView);
                    }
                });
            }
        });
    }

    SmartRefreshLayout mRefreshLayout;
    RecyclerView rvContent;
    HomeArticleListAdapter mArticleListAdapter;
    List<HomeArticleListResponse> mArticleList;
    int curPage;
    int cid;
    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.item_hierachy_content, null);
        mRefreshLayout=contentView.findViewById(R.id.refresh);
        rvContent=contentView.findViewById(R.id.rv_content);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage=0;
                mViewModel.fetchClassifyArticle(curPage,cid);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.fetchClassifyArticle(++curPage,cid);
            }
        });

        mArticleList=new ArrayList<>();
        LinearLayoutManager vLinearLayoutManager=new LinearLayoutManager(getContext());
        mArticleListAdapter=new HomeArticleListAdapter(R.layout.item_home_article_list,mArticleList);
        rvContent.setLayoutManager(vLinearLayoutManager);
        rvContent.setAdapter(mArticleListAdapter);
        return contentView;
    }

    private void getClassifyResult() {
        mViewModel.getLiveDataClassify().observe(this, new Observer<Resource<ResponseInfo<List<HierachyClassifyResponce>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HierachyClassifyResponce>>> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HierachyClassifyResponce>>>() {
                    @Override
                    public void onSuccess(ResponseInfo<List<HierachyClassifyResponce>> resource) {
                        List<HierachyClassifyResponce> vData = resource.data;
                        if (vData==null)return;
                        firstList.addAll(vData);
                        HierachyClassifyResponce vHierachyClassifyResponce = firstList.get(0);
                        vHierachyClassifyResponce.status=true;
                        firstAdapter.notifyDataSetChanged();
                        List<HierachyClassifyResponce.ChildrenBean> vChildren = vHierachyClassifyResponce.children;
                        if (vChildren!=null){
                            secondList.addAll(vChildren);
                            HierachyClassifyResponce.ChildrenBean vChildrenBean = vChildren.get(0);
                            vChildrenBean.status=true;
                            secondAdapter.notifyDataSetChanged();
                            curPage=0;
                            cid=vChildrenBean.id;
                            mViewModel.fetchClassifyArticle(curPage,cid);

                        }
                    }

                    @Override
                    public void onLoading() {
                        mArticleListAdapter.setEmptyView(loadingView);
                    }

                    @Override
                    public void onError(String errorMessage) {

                    }
                });
            }
        });
    }

    private View getMenuView() {
        View menuView = LayoutInflater.from(getContext()).inflate(R.layout.item_hierachy_menu, null);
        firstList=new ArrayList<>();
        rvFirst=menuView.findViewById(R.id.menu_rv_first);
        LinearLayoutManager vLinearLayoutManager=new LinearLayoutManager(getContext());
        firstAdapter=new HierachyClassfyFirstAdapter(R.layout.item_rv_hierachy_classify,firstList);
        rvFirst.setLayoutManager(vLinearLayoutManager);
        rvFirst.setAdapter(firstAdapter);

        rvSecond=menuView.findViewById(R.id.menu_rv_second);
        secondList=new ArrayList<>();
        secondAdapter=new HierachyClassfySecondAdapter(R.layout.item_rv_hierachy_classify,secondList);
        LinearLayoutManager vLinearLayoutManager2=new LinearLayoutManager(getContext());
        rvSecond.setLayoutManager(vLinearLayoutManager2);
        rvSecond.setAdapter(secondAdapter);

        firstAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HierachyClassifyResponce vHierachyClassifyResponce = firstList.get(position);
                for (int i = 0; i < firstList.size(); i++) {
                    HierachyClassifyResponce vHierachyClassifyResponce1 = firstList.get(i);
                    vHierachyClassifyResponce1.status=false;
                }
                vHierachyClassifyResponce.status=true;
                firstAdapter.notifyDataSetChanged();
                List<HierachyClassifyResponce.ChildrenBean> vChildren = vHierachyClassifyResponce.children;
                if (vChildren!=null){
                    secondList.clear();
                    secondList.addAll(vChildren);
                }

                secondAdapter.notifyDataSetChanged();
            }
        });

        secondAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HierachyClassifyResponce.ChildrenBean vChildrenBean = secondList.get(position);
                if (vChildrenBean!=null){
                    for (int i = 0; i < secondList.size(); i++) {
                        HierachyClassifyResponce.ChildrenBean vChildrenBean1 = secondList.get(i);
                        vChildrenBean1.status=false;
                    }
                    vChildrenBean.status=true;
                    secondAdapter.notifyDataSetChanged();
                    cid=vChildrenBean.id;
                    curPage=0;
                    mViewModel.fetchClassifyArticle(curPage,cid);
                    hierachyDropDwonMenu.closeMenu();
                }
            }
        });

        return menuView;
    }

}
