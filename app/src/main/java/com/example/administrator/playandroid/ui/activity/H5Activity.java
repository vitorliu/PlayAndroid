/*
 *
 */
package com.example.administrator.playandroid.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.base.XActivity;

import butterknife.BindView;


/**
 * The Class H5Activity.
 */
@SuppressLint("JavascriptInterface")
public class H5Activity extends XActivity {

    private static final String URL = "URL";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.html_wv_content)
    WebView htmlWvContent;


    private String url;

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, H5Activity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_html_five;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        url = getIntent().getStringExtra(URL);
        setWebVew(htmlWvContent);
        if (!TextUtils.isEmpty(url)) {
            setWebListener();
            htmlWvContent.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }


    private void back() {
        if (htmlWvContent.canGoBack()) {
            htmlWvContent.goBack();
        } else {
            finish();
        }
        finish();
    }

    private void setWebListener() {
        htmlWvContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        htmlWvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }
        });

    }

    /**
     * Sets the web vew.
     *
     * @param webView the new web vew
     */
    private void setWebVew(WebView webView) {
        WebSettings settings = webView.getSettings();//获得浏览器设置
        settings.setJavaScriptEnabled(true);  //支持js

        settings.setUseWideViewPort(true);  //将图片调整到适合webview的大小

        settings.setSupportZoom(false);  //支持缩放

        settings.setAllowContentAccess(true);

        settings.setAppCacheEnabled(false);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重      新布局

        settings.supportMultipleWindows();  //多窗口

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //关闭webview中缓存

        settings.setAllowFileAccess(true);  //设置可以访问文件

        settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点

        settings.setBuiltInZoomControls(false); //设置支持缩放

        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片

        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);


    }
}

