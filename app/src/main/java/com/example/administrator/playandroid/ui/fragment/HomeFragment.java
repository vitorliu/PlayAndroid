package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.adapter.HomeArticleListAdapter;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.CollectViewModel;
import com.example.administrator.playandroid.architeture.viewmodel.HomeFragmentViewModel;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;
import com.example.administrator.playandroid.bean.HomeArticleResponce;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.HomeCommonUseWebResponse;
import com.example.administrator.playandroid.bean.HomeSeacherHotWordResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;
import com.example.administrator.playandroid.ui.activity.H5Activity;
import com.example.administrator.playandroid.ui.activity.LoginActivity;
import com.example.administrator.playandroid.ui.activity.MainActivity;
import com.example.administrator.playandroid.utils.GlideImageLoader;
import com.example.administrator.playandroid.utils.GlobalUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
public class HomeFragment extends XFragment {

    @BindView(R.id.home_rv_aticle_list)
    RecyclerView homeRvAticleList;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @Inject
    CollectViewModel mCollectViewModel;
    @Inject
    HomeFragmentViewModel mViewModel;
    List<HomeArticleListResponse> mHomeArticleList;
    HomeArticleListAdapter mArticleListAdapter;

    /**
     * 头部
     */
    Banner homeBanner;
    TagFlowLayout homeTagSeacherHotWord;
    TagFlowLayout homeTagCommonUseWeb;
    RecyclerView topRv;
    HomeArticleListAdapter mTopArticleListAdapter;
    List<HomeArticleListResponse> mHomeTopArticleList;


    int curPage;
    int topArticleCollectPosition=-1;
    int nomalArticleCollectPosition=-1;
    @Inject
    public HomeFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setRefresh();
        setupRecycle();
        getBannerResult();
        getSeacherHotWordResult();
        getCommonUseWebResult();
        getTopArticleListResult();
        getArricleListResult();
        getCollectResult();
        getUncollectResult();
    }

    private void getCollectResult() {
        mCollectViewModel.getLiveDataCollect().observe(this, pResponseInfoResource -> {
            switch (pResponseInfoResource.status){
                case SUCCESS:
                    ((MainActivity)getActivity()).showToast("收藏成功");
                    if (nomalArticleCollectPosition!=-1){
                        HomeArticleListResponse vHomeArticleListResponse = mHomeArticleList.get(nomalArticleCollectPosition);
                        vHomeArticleListResponse.collect=true;
                        mArticleListAdapter.notifyDataSetChanged();
                    }
                    if (topArticleCollectPosition!=-1){
                        HomeArticleListResponse vHomeArticleListResponse = mHomeTopArticleList.get(topArticleCollectPosition);
                        vHomeArticleListResponse.collect=true;
                        mTopArticleListAdapter.notifyDataSetChanged();
                    }
                    break;
                case ERROR:
                    ((MainActivity)getActivity()).showToast(pResponseInfoResource.data.errorMsg);
                    break;
            }
        });
    }

    private void getUncollectResult() {
        mCollectViewModel.getLiveDataUncollect().observe(this, pResponseInfoResource -> {
            switch (pResponseInfoResource.status){
                case SUCCESS:
                    ((MainActivity)getActivity()).showToast("取消收藏成功");
                    if (nomalArticleCollectPosition!=-1){
                        HomeArticleListResponse vHomeArticleListResponse = mHomeArticleList.get(nomalArticleCollectPosition);
                        vHomeArticleListResponse.collect=false;
                        mArticleListAdapter.notifyDataSetChanged();
                    }
                    if (topArticleCollectPosition!=-1){
                        HomeArticleListResponse vHomeArticleListResponse = mHomeTopArticleList.get(topArticleCollectPosition);
                        vHomeArticleListResponse.collect=false;
                        mTopArticleListAdapter.notifyDataSetChanged();
                    }
                    break;
                case ERROR:
                    ((MainActivity)getActivity()).showToast(pResponseInfoResource.data.errorMsg);
                    break;
            }
        });
    }

    private void setRefresh() {
        refresh.setOnRefreshListener(refreshLayout -> {
            curPage=0;
            mViewModel.fetchArticleList(curPage);
        });
        refresh.setOnLoadMoreListener(refreshLayout -> mViewModel.fetchArticleList(++curPage));
    }

    private void setupRecycle() {
        mHomeArticleList = new ArrayList<>();
        mArticleListAdapter = new HomeArticleListAdapter(R.layout.item_home_article_list, mHomeArticleList);
        View headerView = getHeaderView();
        mArticleListAdapter.addHeaderView(headerView);
        LinearLayoutManager vLinearLayoutManager = new LinearLayoutManager(getContext());
        homeRvAticleList.setLayoutManager(vLinearLayoutManager);
        homeRvAticleList.setAdapter(mArticleListAdapter);
        mArticleListAdapter.setOnItemClickListener((adapter, view, position) -> H5Activity.launch(getContext(), mHomeArticleList.get(position).link));
        mArticleListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (((MainActivity)getActivity()).checkLogin())return;
            HomeArticleListResponse vHomeArticleListResponse = mHomeArticleList.get(position);
            nomalArticleCollectPosition=position;
            if (vHomeArticleListResponse.collect){
                uncollectArticle(vHomeArticleListResponse.id);
            }else {
                collectArticle(vHomeArticleListResponse.id);
            }
        });
    }



    private View getHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.item_home_header, null);
        homeBanner = headerView.findViewById(R.id.home_banner);
        homeTagSeacherHotWord = headerView.findViewById(R.id.home_tag_seacher_hot_word);
        homeTagCommonUseWeb = headerView.findViewById(R.id.home_tag_common_use_web);

        topRv=headerView.findViewById(R.id.home_top_article);
        mHomeTopArticleList=new ArrayList<>();
        mTopArticleListAdapter=new HomeArticleListAdapter(R.layout.item_home_article_list, mHomeTopArticleList);
        LinearLayoutManager vLinearLayoutManager = new LinearLayoutManager(getContext());
        topRv.setLayoutManager(vLinearLayoutManager);
        topRv.setAdapter(mTopArticleListAdapter);
        mTopArticleListAdapter.setOnItemClickListener((adapter, view, position) -> H5Activity.launch(getContext(), mHomeTopArticleList.get(position).link));
        mTopArticleListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (((MainActivity)getActivity()).checkLogin())return;
            HomeArticleListResponse vHomeArticleListResponse = mHomeTopArticleList.get(position);
            topArticleCollectPosition=position;
            if (vHomeArticleListResponse.collect){
                uncollectArticle(vHomeArticleListResponse.id);
            }else {
                collectArticle(vHomeArticleListResponse.id);
            }
        });
        return headerView;
    }

    private void collectArticle(int id) {
        mCollectViewModel.collect(id);
    }

    private void uncollectArticle(int id) {
        mCollectViewModel.unCollect(id);
    }

    @Override
    protected boolean isShowStateView() {
        return true;
    }

    private void getArricleListResult() {
        mViewModel.fetchArticleList(0);
        mViewModel.getLiveDataHomeArticleList().observe(this, pResponseInfoResource -> {
            refresh.finishRefresh();
            refresh.finishLoadMore();
            NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<HomeArticleResponce>>() {
                @Override
                public void onSuccess(ResponseInfo<HomeArticleResponce> resource) {
                    HomeArticleResponce vData = resource.data;
                    if (vData==null){
                        mArticleListAdapter.setEmptyView(emptyView);
                        return;
                    }
                    if (curPage==0){
                        mHomeArticleList.clear();
                    }
                    mHomeArticleList.addAll(vData.datas);
                    mArticleListAdapter.notifyDataSetChanged();
                    if (vData.over){
                        refresh.setEnableLoadMore(false);
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


        });
    }

    private void getTopArticleListResult() {
        mViewModel.getLiveDataHomeTopArticleList().observe(this, pResponseInfoResource -> NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeArticleListResponse>>>() {
            @Override
            public void onSuccess(ResponseInfo<List<HomeArticleListResponse>> resource) {

                List<HomeArticleListResponse> vData = resource.data;
                if (vData != null) {
                    mHomeTopArticleList.addAll(vData);
                }
                mTopArticleListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onError(String errorMessage) {

            }
        }));
    }

    private void getCommonUseWebResult() {
        mViewModel.getLiveDataHomeCommonUseWeb().observe(this, pResponseInfoResource -> NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeCommonUseWebResponse>>>() {
            @Override
            public void onSuccess(ResponseInfo<List<HomeCommonUseWebResponse>> resource) {
                final List<HomeCommonUseWebResponse> vData = resource.data;
                if (vData != null) {
                    homeTagCommonUseWeb.setAdapter(new TagAdapter<HomeCommonUseWebResponse>(vData) {
                        @Override
                        public View getView(FlowLayout parent, int position, HomeCommonUseWebResponse pHomeCommonUseWebResponse) {
                            TextView tv = getTextView();
                            tv.setText(pHomeCommonUseWebResponse.name);
                            return tv;
                        }

                    });
                    homeTagCommonUseWeb.setOnTagClickListener((view, position, parent) -> {
                        H5Activity.launch(getContext(), vData.get(position).link);
                        return false;
                    });
                }
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onError(String errorMessage) {

            }
        }));
    }

    private void getSeacherHotWordResult() {
        mViewModel.getLiveDataSeacherHotWord().observe(this, pResponseInfoResource -> NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeSeacherHotWordResponse>>>() {
            @Override
            public void onSuccess(ResponseInfo<List<HomeSeacherHotWordResponse>> resource) {
                List<HomeSeacherHotWordResponse> vData = resource.data;
                if (vData != null) {
                    homeTagSeacherHotWord.setAdapter(new TagAdapter<HomeSeacherHotWordResponse>(vData) {
                        @Override
                        public View getView(FlowLayout parent, int position, HomeSeacherHotWordResponse pHomeSeacherHotWordResponse) {
                            TextView tv = getTextView();
                            tv.setText(pHomeSeacherHotWordResponse.name);
                            return tv;
                        }

                    });
                }
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onError(String errorMessage) {

            }
        }));
    }

    private TextView getTextView() {
        TextView tv = new TextView(getActivity());
        ViewGroup.LayoutParams vLayoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((RadioGroup.LayoutParams) vLayoutParams).setMargins(20, 10, 20, 10);
        tv.setPadding(10, 5, 10, 5);
        tv.setBackgroundResource(R.drawable.shape_tag_bg);
        tv.setLayoutParams(vLayoutParams);
        return tv;
    }

    private void getBannerResult() {
        mViewModel.getLiveDataHomeBanner().observe(this, pResponseInfoResource -> NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeBannerResponse>>>() {
            @Override
            public void onSuccess(ResponseInfo<List<HomeBannerResponse>> resource) {
                //设置图片加载器
                List<HomeBannerResponse> vData = resource.data;
                List<String> images = new ArrayList<>();
                final List<String> urlList = new ArrayList<>();
                if (vData != null) {
                    if (vData != null) {
                        for (int i = 0; i < vData.size(); i++) {
                            HomeBannerResponse vHomeBannerResponse = vData.get(i);
                            if (vHomeBannerResponse != null) {
                                images.add(vHomeBannerResponse.imagePath);
                                urlList.add(vHomeBannerResponse.url);
                            }
                        }
                    }
                }
                homeBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        H5Activity.launch(getActivity(), urlList.get(position));
                    }
                });
                homeBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                homeBanner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                homeBanner.start();
            }

            @Override
            public void onLoading() {

            }

            @Override
            public void onError(String errorMessage) {

            }
        }));
    }

}
