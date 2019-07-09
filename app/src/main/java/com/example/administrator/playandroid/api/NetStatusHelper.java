package com.example.administrator.playandroid.api;

import com.example.administrator.playandroid.base.bean.Resource;
import com.example.administrator.playandroid.bean.ResponseInfo;

/**
 * Created by Administrator on 2019/7/9.
 * <p>Copyright 2019 Success101.</p>
 */
public class NetStatusHelper {

    public static <T extends ResponseInfo> void handStatus(Resource<T> pResource,StatusCallBack<T>pCallBack){
        switch (pResource.status){
            case LOADING:
                pCallBack.onLoading();
                break;
            case SUCCESS:
                T vData = pResource.data;
                if (vData!=null){
                    pCallBack.onSuccess(vData);
                }
                break;
            case ERROR:
                pCallBack.onError("服务器或网络异常");
                break;
        }
    }

    public interface StatusCallBack<T> {
        void onSuccess(T resource);

        void onLoading();

        void onError(String errorMessage);
    }
}
