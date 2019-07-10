package com.example.administrator.playandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 * <p>Copyright 2019 Success101.</p>
 */
public class ProjectListResponce {

    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<DatasBean> datas;


    public static class DatasBean {
        /**
         * apkLink :
         * author : TzuChiangLi
         * chapterId : 294
         * chapterName : 完整项目
         * collect : false
         * courseId : 13
         * desc : 当时毕业公司安排我学习Android的开发以快速开发项目，所以在公司一直MVC的模式开发，在看了现在的主流及趋势后，发现MVP是进步路上的必修课，所以就参考了很多大神的项目学习MVP的写法和思路。
         * envelopePic : https://wanandroid.com/blogimgs/bf9ed860-3ab1-4bea-9c9a-6de3c75e861b.png
         * fresh : false
         * id : 8658
         * link : http://www.wanandroid.com/blog/show/2617
         * niceDate : 2019-07-01
         * origin :
         * prefix :
         * projectLink : https://github.com/TzuChiangLi/WanAndroid
         * publishTime : 1561983121000
         * superChapterId : 294
         * superChapterName : 开源项目主Tab
         * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
         * title : WanAndroid 个人第一个练手项目分享
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
        public List<TagsBean> tags;


        public static class TagsBean {
            /**
             * name : 项目
             * url : /project/list/1?cid=294
             */

            public String name;
            public String url;
        }
    }
}
