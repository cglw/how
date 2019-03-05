package com.prettyyes.user.app.ui.common;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.utils.PermissionUtils;

import org.xutils.view.annotation.ViewInject;

public class AppWebActivity extends BaseActivity {

    @ViewInject(R.id.webview_appweb)
    private AppWebView appWebView;
    private String loadurl;
    private boolean isneeedDownload = false;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        loadurl = getIntent().getStringExtra("url");
        isneeedDownload = getIntent().getBooleanExtra("isneeedDownload", false);

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_app_web;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initViews() {
        setActivtytitle("How");
        if (isneeedDownload) {
            appWebView.setIsdownload(isneeedDownload);
        }
        appWebView.setLoadurl(loadurl);

    }


    @Override
    protected void loadData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                showToastShort("权限申请成功，你可以继续操作");
            }
        });
    }

    @Override
    protected void onPause() {
        if (TextUtils.isEmpty(loadurl))
            AppStatistics.onPageEnd(getThis(),"WebDetailPage_" + loadurl);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(loadurl))
            AppStatistics.onPageStart(getThis(),"WebDetailPage_" + loadurl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appWebView != null)
            appWebView.loadContent("");
    }
}
