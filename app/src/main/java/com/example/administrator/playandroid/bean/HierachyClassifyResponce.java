package com.example.administrator.playandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/9.
 * <p>Copyright 2019 Success101.</p>
 */
public class HierachyClassifyResponce {

    /**
     * children : [{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"userControlSetTop":false,"visible":1}]
     * courseId : 13
     * id : 150
     * name : 开发环境
     * order : 1
     * parentChapterId : 0
     * userControlSetTop : false
     * visible : 1
     */

    public int courseId;
    public int id;
    public String name;
    public int order;
    public int parentChapterId;
    public boolean userControlSetTop;
    public int visible;
    public List<ChildrenBean> children;
    public boolean status;

    public static class ChildrenBean {
        /**
         * children : []
         * courseId : 13
         * id : 60
         * name : Android Studio相关
         * order : 1000
         * parentChapterId : 150
         * userControlSetTop : false
         * visible : 1
         */

        public int courseId;
        public int id;
        public String name;
        public int order;
        public int parentChapterId;
        public boolean userControlSetTop;
        public int visible;
        public List<?> children;
        public boolean status;

    }
}
