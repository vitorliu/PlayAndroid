package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
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
import com.example.administrator.playandroid.architeture.viewmodel.HomeFragmentViewModel;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.HomeCommonUseWebResponse;
import com.example.administrator.playandroid.bean.HomeSeacherHotWordResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;
import com.example.administrator.playandroid.ui.activity.H5Activity;
import com.example.administrator.playandroid.utils.GlideImageLoader;
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
    @Inject
    HomeFragmentViewModel mViewModel;
    @BindView(R.id.home_rv_aticle_list)
    RecyclerView homeRvAticleList;

    List<HomeArticleListResponse> mHomeArticleList;
    HomeArticleListAdapter mArticleListAdapter;
    List<HomeArticleListResponse> mHomeTopArticleList;

    Banner homeBanner;
    TagFlowLayout homeTagSeacherHotWord;
    TagFlowLayout homeTagCommonUseWeb;

    @Inject
    public HomeFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
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
        homeBanner=headerView.findViewById(R.id.home_banner);
        homeTagSeacherHotWord=headerView.findViewById(R.id.home_tag_seacher_hot_word);
        homeTagCommonUseWeb=headerView.findViewById(R.id.home_tag_common_use_web);
        return headerView;
    }

    private void getArricleListResult() {
        mViewModel.fetchArticleList(0);
        mViewModel.getLiveDataHomeArticleList().observe(this, new Observer<Resource<ResponseInfo<List<HomeArticleListResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeArticleListResponse>>> pResponseInfoResource) {
                ResponseInfo<List<HomeArticleListResponse>> vData = pResponseInfoResource.data;
                if (vData != null) {
                    mHomeArticleList.addAll(mHomeTopArticleList!=null?mHomeTopArticleList.size():0, vData.data);
                }
                mArticleListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getTopArticleListResult() {
        mViewModel.getLiveDataHomeTopArticleList().observe(this, new Observer<Resource<ResponseInfo<List<HomeArticleListResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeArticleListResponse>>> pResponseInfoResource) {
                ResponseInfo<List<HomeArticleListResponse>> vData = pResponseInfoResource.data;
                if (vData != null) {
                    if (mHomeTopArticleList == null) {
                        mHomeTopArticleList = new ArrayList<>();
                    }
                    mHomeTopArticleList.addAll(vData.data);
                    mHomeArticleList.addAll(mHomeTopArticleList);
                }

            }
        });
    }

    private void getCommonUseWebResult() {
        mViewModel.getLiveDataHomeCommonUseWeb().observe(this, new Observer<Resource<ResponseInfo<List<HomeCommonUseWebResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeCommonUseWebResponse>>> pResponseInfoResource) {
                ResponseInfo<List<HomeCommonUseWebResponse>> vData = pResponseInfoResource.data;
                if (vData != null) {
                    final List<HomeCommonUseWebResponse> dataList = vData.data;
                    homeTagCommonUseWeb.setAdapter(new TagAdapter<HomeCommonUseWebResponse>(dataList) {
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
                            H5Activity.launch(getContext(), dataList.get(position).link);
                            return false;
                        }
                    });
                }
            }
        });
    }

    private void getSeacherHotWordResult() {
        mViewModel.getLiveDataSeacherHotWord().observe(this, new Observer<Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeSeacherHotWordResponse>>> pResponseInfoResource) {
                ResponseInfo<List<HomeSeacherHotWordResponse>> vData = pResponseInfoResource.data;
                if (vData != null) {
                    final List<HomeSeacherHotWordResponse> dataList = vData.data;
                    homeTagSeacherHotWord.setAdapter(new TagAdapter<HomeSeacherHotWordResponse>(dataList) {
                        @Override
                        public View getView(FlowLayout parent, int position, HomeSeacherHotWordResponse pHomeSeacherHotWordResponse) {
                            TextView tv = getTextView();
                            tv.setText(pHomeSeacherHotWordResponse.name);
                            return tv;
                        }

                    });
                }
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
                //设置图片加载器
                ResponseInfo<List<HomeBannerResponse>> vData = pResponseInfoResource.data;
                List<String> images = new ArrayList<>();
                final List<String> urlList = new ArrayList<>();
                if (vData != null) {
                    List<HomeBannerResponse> vBannerResponseList = vData.data;
                    if (vBannerResponseList != null) {
                        for (int i = 0; i < vBannerResponseList.size(); i++) {
                            HomeBannerResponse vHomeBannerResponse = vBannerResponseList.get(i);
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
        });
    }

}
