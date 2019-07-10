package com.example.administrator.playandroid.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class NavigationResponce  {


    /**
     * articles : [{"apkLink":"","author":"小编","chapterId":281,"chapterName":"公司博客","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1894,"link":"https://tech.meituan.com/","niceDate":"2018-01-07","origin":"","prefix":"","projectLink":"","publishTime":1515326436000,"superChapterId":0,"superChapterName":"","tags":[],"title":"美团点评","type":0,"userId":-1,"visible":0,"zan":0},{},{},{},{},{}]
     * cid : 281
     * name : 公司博客
     */

    public int cid;
    public String name;
    public List<ArticlesBean> articles;


    public static class ArticlesBean {
        /**
         * apkLink :
         * author : 小编
         * chapterId : 281
         * chapterName : 公司博客
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 1894
         * link : https://tech.meituan.com/
         * niceDate : 2018-01-07
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1515326436000
         * superChapterId : 0
         * superChapterName :
         * tags : []
         * title : 美团点评
         * type : 0
         * userId : -1
         * visible : 0
         * zan : 0
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
        public List<?> tags;


    }
}
