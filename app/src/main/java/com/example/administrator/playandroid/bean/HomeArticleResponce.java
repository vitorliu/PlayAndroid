package com.example.administrator.playandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/9.
 * <p>Copyright 2019 Success101.</p>
 */
public class HomeArticleResponce {

    /**
     * curPage : 2
     * datas : []
     * offset : 20
     * over : false
     * pageCount : 336
     * size : 20
     * total : 6705
     */

    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<HomeArticleListResponse> datas;

}
