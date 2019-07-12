package com.example.administrator.playandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/12.
 * <p>Copyright 2019 Success101.</p>
 */
public class CollectArticleResponse {

    /**
     * curPage : 1
     * datas : [{"author":"西瓜皮TWO","chapterId":60,"chapterName":"Android Studio相关","courseId":13,"desc":"","envelopePic":"","id":71666,"link":"https://www.jianshu.com/p/dd1faa99dcf1","niceDate":"2小时前","origin":"","originId":8173,"publishTime":1562903143000,"title":"Android 寻找极限编码的「快感」","userId":26958,"visible":0,"zan":0},{"author":"stevewang","chapterId":93,"chapterName":"基础知识","courseId":13,"desc":"","envelopePic":"","id":71648,"link":"https://www.jianshu.com/p/cb5181418c7a","niceDate":"3小时前","origin":"","originId":8690,"publishTime":1562898628000,"title":"Android TouchDelegate详解及优化","userId":26958,"visible":0,"zan":0},{"author":"xiaoyang","chapterId":440,"chapterName":"官方","courseId":13,"desc":"<p>今天问一个老生常谈的问题，很期待大家的回答。<\/p>","envelopePic":"","id":71647,"link":"https://www.wanandroid.com/wenda/show/8685","niceDate":"3小时前","origin":"","originId":8685,"publishTime":1562898615000,"title":"每日一问 Looper.loop为什么不会阻塞掉UI线程？","userId":26958,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 3
     */

    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<DatasBean> datas;

    public static class DatasBean {
        /**
         * author : 西瓜皮TWO
         * chapterId : 60
         * chapterName : Android Studio相关
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 71666
         * link : https://www.jianshu.com/p/dd1faa99dcf1
         * niceDate : 2小时前
         * origin :
         * originId : 8173
         * publishTime : 1562903143000
         * title : Android 寻找极限编码的「快感」
         * userId : 26958
         * visible : 0
         * zan : 0
         */

        public String author;
        public int chapterId;
        public String chapterName;
        public int courseId;
        public String desc;
        public String envelopePic;
        public int id;
        public String link;
        public String niceDate;
        public String origin;
        public int originId;
        public long publishTime;
        public String title;
        public int userId;
        public int visible;
        public int zan;

    }
}
