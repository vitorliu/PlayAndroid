package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
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
import com.example.administrator.playandroid.api.NetStatusHelper;
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
import com.example.administrator.playandroid.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @Inject
    public HomeFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage=0;
                mViewModel.fetchArticleList(curPage);
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.fetchArticleList(++curPage);
            }
        });
        setupRecycle();
        getBannerResult();
        getSeacherHotWordResult();
        getCommonUseWebResult();
        getTopArticleListResult();
        getArricleListResult();

    }

    private void setupRecycle() {
        mHomeArticleList = new ArrayList<>();
        mArticleListAdapter = new HomeArticleListAdapter(R.layout.item_home_article_list, mHomeArticleList);
        View headerView = getHeaderView();
        mArticleListAdapter.addHeaderView(headerView);
        LinearLayoutManager vLinearLayoutManager = new LinearLayoutManager(getContext());
        homeRvAticleList.setLayoutManager(vLinearLayoutManager);
        homeRvAticleList.setAdapter(mArticleListAdapter);
        mArticleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                H5Activity.launch(getContext(), mHomeArticleList.get(position).link);
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
        mTopArticleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                H5Activity.launch(getContext(), mHomeTopArticleList.get(position).link);
            }
        });
        return headerView;
    }

    @Override
    protected boolean isShowStateView() {
        return true;
    }

    private void getArricleListResult() {
        mViewModel.fetchArticleList(0);
        mViewModel.getLiveDataHomeArticleList().observe(this, new Observer<Resource<ResponseInfo<HomeArticleResponce>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<HomeArticleResponce>> pResponseInfoResource) {
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


            }
        });
    }

    private void getTopArticleListResult() {
        mViewModel.getLiveDataHomeTopArticleList().observe(this, new Observer<Resource<ResponseInfo<List<HomeArticleListResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeArticleListResponse>>> pResponseInfoResource) {

                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeArticleListResponse>>>() {
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
                });


            }
        });
    }

    private void getCommonUseWebResult() {
        mViewModel.getLiveDataHomeCommonUseWeb().observe(this, new Observer<Resource<ResponseInfo<List<HomeCommonUseWebResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeCommonUseWebResponse>>> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeCommonUseWebResponse>>>() {
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
                            homeTagCommonUseWeb.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                @Override
                                public boolean onTagClick(View view, int position, FlowLayout parent) {
                                    H5Activity.launch(getContext(), vData.get(position).link);
                                    return false;
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
                });


            }
        });
    }

    private void getSeacherHotWordResult() {
        mViewModel.getLiveDataSeacherHotWord().observe(this, new Observer<Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeSeacherHotWordResponse>>>() {
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
                });


            }
        });
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
        mViewModel.getLiveDataHomeBanner().observe(this, new Observer<Resource<ResponseInfo<List<HomeBannerResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeBannerResponse>>> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<HomeBannerResponse>>>() {
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
                });
            }
        });
    }

}
