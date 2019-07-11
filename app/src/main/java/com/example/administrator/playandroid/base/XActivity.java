package com.example.administrator.playandroid.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.playandroid.R;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Administrator on 2019/6/21.
 * <p>Copyright 2019 Success101.</p>
 */
public abstract class XActivity extends DaggerAppCompatActivity implements UITemplate{
    private Dialog mDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId()>1){
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
        init(savedInstanceState);
    }

    public void showSimpleLoading() {
        showSimpleLoading(null);
    }
    public void showSimpleLoading(String message) {
        if (mDialog == null) {
            mDialog = new Dialog(this, R.style.prompt_progress_dialog);
            mDialog.setContentView(R.layout.dialog_loading);
            mDialog.setCancelable(false);
            mDialog.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        }
        if (mDialog != null && !mDialog.isShowing()) {
            TextView messageView = mDialog.findViewById(R.id.message);
            if (!TextUtils.isEmpty(message)) {
                messageView.setText(message);
            } else {
                messageView.setText("加载中");
            }
            mDialog.show();
        }
    }

    public void hideSimpleLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected void showToast(String msg) {
        Toast vToast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        vToast.setText(msg);
        vToast.setGravity(Gravity.CENTER,0,0);
        vToast.show();
    }

}
