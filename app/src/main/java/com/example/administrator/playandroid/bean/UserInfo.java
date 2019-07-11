package com.example.administrator.playandroid.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by Administrator on 2019/7/11.
 * <p>Copyright 2019 Success101.</p>
 */
@Entity(tableName = "userInfo", indices = {@Index(value = {"primaryId"},
        unique = true)})
public class UserInfo {

    /**
     * admin : false
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 26958
     * nickname : bbb123456
     * password :
     * token :
     * type : 0
     * username : bbb123456
     */
    @PrimaryKey
    public int primaryId=1;

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "admin")
    public boolean admin;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "icon")
    public String icon;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "token")
    public String token;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "username")
    public String username;

    /*@ColumnInfo(name = "chapterTops")
    public List<?> chapterTops;

    @ColumnInfo(name = "collectIds")
    public List<?> collectIds;*/


}
