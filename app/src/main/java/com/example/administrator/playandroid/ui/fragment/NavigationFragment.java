package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.adapter.NavigatonAdapter;
import com.example.administrator.playandroid.api.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.NavigationViewModel;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.NavigationHeaderBean;
import com.example.administrator.playandroid.bean.NavigationResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

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
public class NavigationFragment extends XFragment {
    @BindView(R.id.rv_navigation)
    RecyclerView rvNavigation;
    @Inject
    NavigationViewModel mViewModel;
    @Inject
    public NavigationFragment() {
    }

    List<NavigationHeaderBean> mList;
    NavigatonAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mList=new ArrayList<>();
        mAdapter=new NavigatonAdapter(R.layout.item_rv_navigation_list,R.layout.item_rv_navigation_header,mList);
        LinearLayoutManager vLinearLayoutManager=new LinearLayoutManager(getContext());
        rvNavigation.setLayoutManager(vLinearLayoutManager);
        rvNavigation.setAdapter(mAdapter);

        getNavigationResult();
    }

    private void getNavigationResult() {
        mViewModel.getLiveDataNavigation().observe(this, new Observer<Resource<ResponseInfo<List<NavigationResponce>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<NavigationResponce>>> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<NavigationResponce>>>() {
                    @Override
                    public void onSuccess(ResponseInfo<List<NavigationResponce>> resource) {
                        List<NavigationResponce> vData = resource.data;
                        if (vData==null){
                            mAdapter.setEmptyView(emptyView);
                        }else {
                            for (int i = 0; i < vData.size(); i++) {
                                NavigationHeaderBean vBean=new NavigationHeaderBean(true,vData.get(i).name);
                                NavigationHeaderBean vBean2=new NavigationHeaderBean(vData.get(i));
                                mList.add(vBean);
                                mList.add(vBean2);
                            }
                        }
                        mAdapter.notifyDataSetChanged();
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
            }
        });
    }

    @Override
    protected boolean isShowStateView() {
        return true;
    }
}
