package com.example.administrator.playandroid.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class NavigationHeaderBean extends SectionEntity<NavigationResponce> {


    public NavigationHeaderBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public NavigationHeaderBean(NavigationResponce pNavigationResponce) {
        super(pNavigationResponce);
    }
}
