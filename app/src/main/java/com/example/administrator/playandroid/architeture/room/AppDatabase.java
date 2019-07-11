package com.example.administrator.playandroid.architeture.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.administrator.playandroid.bean.UserInfo;

@Database(entities = {UserInfo.class, }, version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserInfoDao userInfoDao();

}

