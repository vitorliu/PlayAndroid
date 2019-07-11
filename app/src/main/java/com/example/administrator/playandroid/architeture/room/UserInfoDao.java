package com.example.administrator.playandroid.architeture.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.administrator.playandroid.bean.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {//primaryId 唯一，永遠只有一個

    @Query("SELECT * FROM userInfo")
    List<UserInfo> getAll();

    @Query("SELECT * FROM userInfo WHERE primaryId = 1")
    LiveData<UserInfo> getUserInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserInfo... userInfos);

    @Query("DELETE FROM userInfo WHERE primaryId =  1")
    void delete( );

    @Delete
    void delete(UserInfo pUserInfo);

    @Update
    int update(UserInfo pUserInfo);
}
