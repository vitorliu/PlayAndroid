package com.example.administrator.playandroid.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.adapter.ProjectContentAdapter;
import com.example.administrator.playandroid.adapter.ProjectMenuAdapter;
import com.example.administrator.playandroid.api.helper.NetStatusHelper;
import com.example.administrator.playandroid.architeture.viewmodel.ProjectViewModel;
import com.example.administrator.playandroid.base.XFragment;
import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ProjectClassifyResponce;
import com.example.administrator.playandroid.bean.ProjectListResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;
import com.example.administrator.playandroid.ui.activity.H5Activity;
import com.example.administrator.playandroid.ui.activity.MainActivity;
import com.example.administrator.playandroid.ui.view.DropDownMenu;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
public class ProjectFragment extends XFragment {
    @BindView(R.id.project_drop_dwon_menu)
    DropDownMenu projectDropDwonMenu;
    @Inject
    ProjectViewModel mViewModel;

    List<View> popuviews;
    List<String> headerList;

    int curPage = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int cid;

    @Inject
    public ProjectFragment() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(toolbar,"项目");
        headerList = new ArrayList<>(1);
        headerList.add("选择项目类型");
        View menu = getMenuView();
        popuviews = new ArrayList<>(1);
        popuviews.add(menu);
        View contentView = getContentView();
        projectDropDwonMenu.setDropDownMenu(headerList, popuviews, contentView);

        getClassifyResult();
        getProjectListResult();
    }

    private void getProjectListResult() {
        mViewModel.getLiveDataProjectList().observe(this, new Observer<Resource<ResponseInfo<ProjectListResponce>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<ProjectListResponce>> pResponseInfoResource) {
                mRefreshLayout.finishRefresh();
                mRefreshLayout.finishLoadMore();
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<ProjectListResponce>>() {
                    @Override
                    public void onSuccess(ResponseInfo<ProjectListResponce> resource) {
                        ProjectListResponce vData = resource.data;
                        if (vData != null) {
                            if (curPage == 1) {
                                mDatasBeanList.clear();
                            }
                            mDatasBeanList.addAll(vData.datas);
                            if (vData.over) {
                                mRefreshLayout.setEnableLoadMore(false);
                            }
                        }
                        mContentAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onLoading() {
                        mContentAdapter.setEmptyView(loadingView);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        mContentAdapter.setEmptyView(errorView);
                    }
                });
            }
        });
    }

    private void getClassifyResult() {
        mViewModel.getLiveDataProjectClassify().observe(this, new Observer<Resource<ResponseInfo<List<ProjectClassifyResponce>>>>() {
            @Override
            public void onChanged(@Nullable Resource<ResponseInfo<List<ProjectClassifyResponce>>> pResponseInfoResource) {
                NetStatusHelper.handStatus(pResponseInfoResource, new NetStatusHelper.StatusCallBack<ResponseInfo<List<ProjectClassifyResponce>>>() {
                    @Override
                    public void onSuccess(ResponseInfo<List<ProjectClassifyResponce>> resource) {
                        List<ProjectClassifyResponce> vData = resource.data;
                        if (vData != null) {
                            mClassifyResponceList.addAll(vData);
                            mMenuAdapter.notifyDataSetChanged();
                            ProjectClassifyResponce vProjectClassifyResponce = mClassifyResponceList.get(0);
                            curPage = 1;
                            cid = vProjectClassifyResponce.id;
                            mViewModel.fetchProjectListData(curPage, cid);
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

    @Override
    protected boolean isShowStateView() {
        return true;
    }


    RecyclerView rvContent;
    SmartRefreshLayout mRefreshLayout;
    List<ProjectListResponce.DatasBean> mDatasBeanList;
    ProjectContentAdapter mContentAdapter;

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.item_project_rv_content_list, null);
        rvContent = contentView.findViewById(R.id.rv_project_content);
        mRefreshLayout = contentView.findViewById(R.id.refresh);
        mDatasBeanList = new ArrayList<>();
        mContentAdapter = new ProjectContentAdapter(R.layout.item_rv_project_content, mDatasBeanList);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContent.setAdapter(mContentAdapter);

        mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                H5Activity.launch(getContext(), mDatasBeanList.get(position).link);
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 1;
                mViewModel.fetchProjectListData(curPage, cid);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.fetchProjectListData(++curPage, cid);
            }
        });
        return contentView;
    }

    RecyclerView rvMenu;
    ProjectMenuAdapter mMenuAdapter;
    List<ProjectClassifyResponce> mClassifyResponceList;

    private View getMenuView() {
        View menuView = LayoutInflater.from(getContext()).inflate(R.layout.item_project_menu, null);
        rvMenu = menuView.findViewById(R.id.rv_project_menu);
        mClassifyResponceList = new ArrayList<>();
        mMenuAdapter = new ProjectMenuAdapter(R.layout.item_rv_menu_list, mClassifyResponceList);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMenu.setAdapter(mMenuAdapter);
        mMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mClassifyResponceList.size(); i++) {
                    mClassifyResponceList.get(i).isCheck = false;
                }
                ProjectClassifyResponce vProjectClassifyResponce = mClassifyResponceList.get(position);
                vProjectClassifyResponce.isCheck = true;
                mMenuAdapter.notifyDataSetChanged();

                curPage = 1;
                cid = vProjectClassifyResponce.id;
                mViewModel.fetchProjectListData(curPage, cid);
                projectDropDwonMenu.closeMenu();
            }
        });
        return menuView;
    }

}
