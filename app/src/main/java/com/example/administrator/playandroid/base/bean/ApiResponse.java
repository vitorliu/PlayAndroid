
package com.example.administrator.playandroid.base.bean;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import retrofit2.Response;

public class ApiResponse<T> {
    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    public static <T> ApiResponse<T> success(Response<T> response) {
        return new ApiResponse<>(response);
    }

    public static <T> ApiResponse<T> error(Throwable error) {
        ApiResponse easyResponse = new ApiResponse<>(error);
        return easyResponse;
    }


    private ApiResponse(Throwable error) {
        code = 500;
        body = null;
        errorMessage = error.getMessage();
    }

    private ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Log.e("ApiResponse:",ignored + "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }

    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }

}
