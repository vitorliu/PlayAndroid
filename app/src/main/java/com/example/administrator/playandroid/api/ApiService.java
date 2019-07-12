package com.example.administrator.playandroid.api;

import android.arch.lifecycle.LiveData;

import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.bean.CollectArticleResponse;
import com.example.administrator.playandroid.bean.HierachyClassifyResponce;
import com.example.administrator.playandroid.bean.HomeArticleListResponse;
import com.example.administrator.playandroid.bean.HomeArticleResponce;
import com.example.administrator.playandroid.bean.HomeBannerResponse;
import com.example.administrator.playandroid.bean.HomeCommonUseWebResponse;
import com.example.administrator.playandroid.bean.HomeSeacherHotWordResponse;
import com.example.administrator.playandroid.bean.UserInfo;
import com.example.administrator.playandroid.bean.NavigationResponce;
import com.example.administrator.playandroid.bean.ProjectClassifyResponce;
import com.example.administrator.playandroid.bean.ProjectListResponce;
import com.example.administrator.playandroid.bean.ResponseInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/7/2.
 * <p>Copyright 2019 Success101.</p>
 */
public interface ApiService {
    /**
     * 首页轮播
     * @return
     */
    @GET("/banner/json")
    LiveData<ApiResponse<ResponseInfo<List<HomeBannerResponse>>>> requestHomeBanner();

    /**
     * 首页热词搜索
     * @return
     */
    @GET("/hotkey/json")
    LiveData<ApiResponse<ResponseInfo<List<HomeSeacherHotWordResponse>>>> requestHomeSeacherHotWord();

    /**
     * 首页常用网站
     * @return
     */
    @GET("/friend/json")
    LiveData<ApiResponse<ResponseInfo<List<HomeCommonUseWebResponse>>>> requestHomeCommonUseWeb();

    /**
     * 首页文章列表
     * @return
     */
    @GET("/article/list/{page}/json")
    LiveData<ApiResponse<ResponseInfo<HomeArticleResponce>>> requestHomeArticleList(@Path("page")int page);

    /**
     * 首页置顶文章列表
     * @return
     */
    @GET("/article/top/json")
    LiveData<ApiResponse<ResponseInfo<List<HomeArticleListResponse>>>> requestHomeTopArticleList();

    /**
     * 获取体系类别数据
     * @return
     */
    @GET("/tree/json")
    LiveData<ApiResponse<ResponseInfo<List<HierachyClassifyResponce>>>>requestClassifyData();

    /**
     * 获取选择类别的文章列表
     * @param page
     * @param cid
     * @return
     */
    @GET("/article/list/{page}/json")
    LiveData<ApiResponse<ResponseInfo<HomeArticleResponce>>> requestHierachyArticleList(@Path("page")int page, @Query("cid")int cid);

    /**
     * 获取导航数据
     * @return
     */
    @GET("/navi/json")
    LiveData<ApiResponse<ResponseInfo<List<NavigationResponce>>>>requestNavigationDataList();

    /**
     * 获取项目分类
     * @return
     */
    @GET("/project/tree/json")
    LiveData<ApiResponse<ResponseInfo<List<ProjectClassifyResponce>>>>requestProjectClassifyList();

    /**
     * 获取分类下的项目列表
     * @param page
     * @param cid
     * @return
     */
    @GET("/project/list/{page}/json")
    LiveData<ApiResponse<ResponseInfo<ProjectListResponce>>>requestProjectList(@Path("page")int page,@Query("cid")int cid);

    /**
     * 注册
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/user/register")
    LiveData<ApiResponse<ResponseInfo>> requestRegister(@Field("username")String username,@Field("password")String password,@Field("repassword")String repassword);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    LiveData<ApiResponse<ResponseInfo<UserInfo>>> requestLogin(@Field("username")String username, @Field("password")String password);

    /**
     * 登出
     * @return
     */
    @GET("/user/logout/json")
    LiveData<ApiResponse<ResponseInfo>> requestLoginOut();

    /**
     * 收藏文章
     * @param id
     * @return
     */
    @POST("/lg/collect/{id}/json")
    LiveData<ApiResponse<ResponseInfo>>requestCollect(@Path("id")int id);

    /**
     * 取消收藏
     * @param id
     * @return
     */
    @POST("/lg/uncollect_originId/{id}/json")
    LiveData<ApiResponse<ResponseInfo>>requestUncollect(@Path("id")int id);

    /**
     * 收藏文章列表
     * @param page
     * @return
     */
    @GET("/lg/collect/list/{page}/json")
    LiveData<ApiResponse<ResponseInfo<CollectArticleResponse>>>requestCollectArticle(@Path("page") int page);


    /**
     * 收藏列表中取消收藏
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("/lg/uncollect/{id}/json")
    LiveData<ApiResponse<ResponseInfo>>requestCollectListUncollect(@Path("id")int id,@Field("originId")int originId);
}
