package com.example.administrator.playandroid.utils;

import com.example.administrator.playandroid.App;
import com.example.administrator.playandroid.bean.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2019/7/12.
 * <p>Copyright 2019 Success101.</p>
 */
public class GlobalUtils {
    public static boolean isLogin(){
        List<UserInfo> vAll = App.getInstance().getUserInfoDao().getAll();
        return !(vAll==null||vAll.size()==0);
    }
}
