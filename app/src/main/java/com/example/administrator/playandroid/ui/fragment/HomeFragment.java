package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.architeture.viewmodel.HomeFragmentViewModel;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.ResponseInfo;
import com.example.administrator.playandroid.ui.activity.H5Activity;
import com.example.administrator.playandroid.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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
    @BindView(R.id.home_banner)
    Banner homeBanner;

    @Inject
    public HomeFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mViewModel.getLiveDataHomeBanner().observe(this, new Observer<Resource<ResponseInfo<List<HomeBannerResponse>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<HomeBannerResponse>>> pResponseInfoResource) {
                //设置图片加载器
                ResponseInfo<List<HomeBannerResponse>> vData = pResponseInfoResource.data;
                List<String> images=new ArrayList<>();
                final List<String> urlList=new ArrayList<>();
                if (vData!=null){
                    List<HomeBannerResponse> vBannerResponseList = vData.data;
                    if (vBannerResponseList!=null){
                        for (int i = 0; i < vBannerResponseList.size(); i++) {
                            HomeBannerResponse vHomeBannerResponse = vBannerResponseList.get(i);
                            if (vHomeBannerResponse!=null){
                                images.add(vHomeBannerResponse.imagePath);
                                urlList.add(vHomeBannerResponse.url);
                            }
                        }
                    }
                }
                homeBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        H5Activity.launch(getActivity(),urlList.get(position));
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
