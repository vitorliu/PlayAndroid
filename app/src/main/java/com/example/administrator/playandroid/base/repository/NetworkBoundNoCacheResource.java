package com.example.administrator.playandroid.base.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import com.example.administrator.playandroid.base.bean.ApiResponse;
import com.example.administrator.playandroid.base.bean.Resource;


/**
 * no cache
 * @param <ResultType>
 */
public abstract class NetworkBoundNoCacheResource<ResultType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundNoCacheResource() {
        result.setValue((Resource<ResultType>) Resource.loading(null));
        fetchFromNetwork();
    }

    private void fetchFromNetwork() {
        final LiveData<ApiResponse<ResultType>> apiResponse = createCall();
        // we re-attach dbSource as a new source,
        // it will dispatch its latest value quickly
        result.addSource(apiResponse, new Observer<ApiResponse<ResultType>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<ResultType> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(response.body));
                } else {
                    NetworkBoundNoCacheResource.this.onFetchFailed();
                    result.setValue((Resource<ResultType>) Resource.error(response.errorMessage, null));
                }
            }
        });
    }

    protected abstract LiveData<ApiResponse<ResultType>> createCall();

    protected void onFetchFailed() {

    }

    public final LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}
