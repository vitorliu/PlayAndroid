package com.example.administrator.playandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XActivity;
import com.example.administrator.playandroid.ui.fragment.HomeFragment;
import com.example.administrator.playandroid.ui.fragment.KnowleageHierachyFragment;
import com.example.administrator.playandroid.ui.fragment.MineFragment;
import com.example.administrator.playandroid.ui.fragment.NavigationFragment;
import com.example.administrator.playandroid.ui.fragment.ProjectFragment;
import com.example.administrator.playandroid.utils.GlobalUtils;

import javax.inject.Inject;

import butterknife.BindView;

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
    FragmentManager supportFragmentManager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_home:
                        switchFragment(mHomeFragment, "home");
                        break;
                    case R.id.main_rb_knowleage_hierachy:
                        switchFragment(mHierachyFragment, "hierachy");
                        break;
                    case R.id.main_rb_navigation:
                        switchFragment(mNavigationFragment, "navigation");
                        break;
                    case R.id.main_rb_project:
                        switchFragment(mProjectFragment, "project");
                        break;
                    case R.id.main_rb_mine:
                        switchFragment(mMineFragment, "mine");
                        break;
                }
            }
        });
        mainRg.getChildAt(0).performClick();
    }

    private void switchFragment(Fragment pFragment, String tag) {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction vFragmentTransaction = supportFragmentManager.beginTransaction();
        if (mShowFragment != null) {
            vFragmentTransaction.hide(mShowFragment);
        }
        Fragment vFragmentByTag = supportFragmentManager.findFragmentByTag(tag);
        if (vFragmentByTag != null) {
            vFragmentTransaction.show(vFragmentByTag);
        } else {
            vFragmentByTag = pFragment;
            vFragmentTransaction.add(R.id.main_container, vFragmentByTag, tag);
        }
        mShowFragment = vFragmentByTag;
        vFragmentTransaction.commit();
    }

    public boolean checkLogin() {
        if (!GlobalUtils.isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        return false;
    }

    public void setTitle(Toolbar pToolbar,String title) {
        setSupportActionBar(pToolbar);
        ActionBar vActionBar = getSupportActionBar();
        vActionBar.setDisplayHomeAsUpEnabled(false);
        vActionBar.setTitle(title);

    }

}
