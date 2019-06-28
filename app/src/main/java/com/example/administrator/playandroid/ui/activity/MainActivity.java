package com.example.administrator.playandroid.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XActivity;
import com.example.administrator.playandroid.ui.fragment.HomeFragment;
import com.example.administrator.playandroid.ui.fragment.KnowleageHierachyFragment;
import com.example.administrator.playandroid.ui.fragment.MineFragment;
import com.example.administrator.playandroid.ui.fragment.NavigationFragment;
import com.example.administrator.playandroid.ui.fragment.ProjectFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends XActivity {

    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    @Inject
    HomeFragment mHomeFragment;
    @Inject
    KnowleageHierachyFragment mHierachyFragment;
    @Inject
    NavigationFragment mNavigationFragment;
    @Inject
    ProjectFragment mProjectFragment;
    @Inject
    MineFragment mMineFragment;

    Fragment mShowFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_rb_home:
                        switchFragment(mHomeFragment,"home");
                        break;
                    case R.id.main_rb_knowleage_hierachy:
                        switchFragment(mHierachyFragment,"hierachy");
                        break;
                    case R.id.main_rb_navigation:
                        switchFragment(mNavigationFragment,"navigation");
                        break;
                    case R.id.main_rb_project:
                        switchFragment(mProjectFragment,"project");
                        break;
                    case R.id.main_rb_mine:
                        switchFragment(mMineFragment,"mine");
                        break;
                }
            }
        });
        mainRg.getChildAt(0).performClick();
    }

    private void switchFragment(Fragment pFragment,String tag){
        FragmentManager vSupportFragmentManager = getSupportFragmentManager();
        FragmentTransaction vFragmentTransaction = vSupportFragmentManager.beginTransaction();
        if (mShowFragment!=null){
            vFragmentTransaction.hide(mShowFragment);
        }
        Fragment vFragmentByTag = vSupportFragmentManager.findFragmentByTag(tag);
        if (vFragmentByTag!=null){
            vFragmentTransaction.show(vFragmentByTag);
        }else {
            vFragmentByTag=pFragment;
            vFragmentTransaction.add(R.id.main_container,vFragmentByTag,tag);
        }
        mShowFragment=vFragmentByTag;
        vFragmentTransaction.commit();
    }

}
