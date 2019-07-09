package com.example.administrator.playandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/4.
 * <p>Copyright 2019 Success101.</p>
 */
public class HomeArticleListResponse {


    /**
     * apkLink :
     * author : xiaoyang
     * chapterId : 440
     * chapterName : 官方
     * collect : false
     * courseId : 13
     * desc : <p>可以仔细思考下ANR是如何产生的？</p><br><p>我在 UI 线程执行一个非常耗时的操作一定会出现 ANR 弹框吗？</p><br><p>本周 3/3 </p>
     * envelopePic :
     * fresh : false
     * id : 8650
     * link : https://www.wanandroid.com/wenda/show/8650
     * niceDate : 2019-06-30
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1561905215000
     * superChapterId : 440
     * superChapterName : 问答
     * tags : [{"name":"问答","url":"/article/list/0?cid=440"}]
     * title : 每日一问 ANR的产生的原理是什么，AMS中涉及ANR的代码有哪些？
     * type : 0
     * userId : 2
     * visible : 1
     * zan : 17
     */

    public String apkLink;
    public String author;
    public int chapterId;
    public String chapterName;
    public boolean collect;
    public int courseId;
    public String desc;
    public String envelopePic;
    public boolean fresh;
    public int id;
    public String link;
    public String niceDate;
    public String origin;
    public String prefix;
    public String projectLink;
    public long publishTime;
    public int superChapterId;
    public String superChapterName;
    public String title;
    public int type;
    public int userId;
    public int visible;
    public int zan;
    public List<TagsBean> tags;


    public static class TagsBean {
        /**
         * name : 问答
         * url : /article/list/0?cid=440
         */

        public String name;
        public String url;
    }
}
