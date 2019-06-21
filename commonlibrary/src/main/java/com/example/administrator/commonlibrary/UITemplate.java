package com.example.administrator.commonlibrary;

import android.os.Bundle;

/**
 * Created by Administrator on 2019/6/21.
 * <p>Copyright 2019 Success101.</p>
 */
public interface UITemplate {
    int getLayoutId();
    void init(Bundle savedInstanceState);
}
