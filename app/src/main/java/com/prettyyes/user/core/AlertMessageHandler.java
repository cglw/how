package com.prettyyes.user.core;

import android.app.Activity;
import android.content.Intent;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.VersionApiImpl;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.account.ACache;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.common.AlertMessageActivity;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.model.common.AppBanner;

import java.io.File;
import java.io.IOException;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/9/26
 * Description: 进入app的弹窗activity
 * 逻辑：每次进入app页面 都会请求这个接口
 * 请求成功吗，没有数据直接结束
 * 有数据，先取缓存，如果缓存有判断缓存的跟请求的一致不，一致就关闭
 * 不一致下载图片，下载成功缓存
 * 没有缓存直接下载
 */
public class AlertMessageHandler {
    private ACache aCache;
    private Activity activity;

    public AlertMessageHandler(Activity activity) {
        aCache = ACache.get(BaseApplication.getAppContext());
        this.activity = activity;
    }

    private String type = "alert_page";

    public void loadAlertMessage() {


        new SystemApiImpl().getBanner(type, new NetReqCallback<AppBanner>() {
            @Override
            public void apiRequestFail(String message,String method) {

            }

            @Override
            public void apiRequestSuccess(AppBanner appBanner,String method) {
//                loadImage(appBanner.getList().get(0).getImg_url(), appBanner.getList().get(0).getImg_url(), appBanner.getList().get(0));

                if (appBanner == null || appBanner.getList().size() <= 0)
                    return;
                AppBanner.ListEntity a = new SPUtils<AppBanner.ListEntity>().get(AppBanner.ListEntity.class, type);
                if (a != null) {
                    //id 不一样说明有新的来了
                    if (appBanner.getList().size() > 0 && appBanner.getList().get(0).getId() != a.getId()) {
                        loadImage(appBanner.getList().get(0).getImg_url(), appBanner.getList().get(0).getImg_url(), appBanner.getList().get(0));
                    }
                } else {
                    if (appBanner.getList().size() > 0)
                        loadImage(appBanner.getList().get(0).getImg_url(), appBanner.getList().get(0).getImg_url(), appBanner.getList().get(0));

                }
            }
        });

    }

    private void loadImage(String url, final String filename, final AppBanner.ListEntity systemAlertMessage) {
        File updateFile = FileUtils.getDiskCacheDir(activity, filename);
        if (!updateFile.exists()) {
            try {
                updateFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new VersionApiImpl().downLoadImg(url, updateFile.getAbsolutePath(), new ProgressCallback() {
            @Override
            public void onFail(String paramString1, String paramString2) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String result) {
                new SPUtils<AppBanner.ListEntity>().save(systemAlertMessage, type);
                startAlertActivity(filename);


            }
        });
    }

    private void startAlertActivity(String filename) {
        if (AppUtil.isActivityRunning(activity, MainActivity.class.getCanonicalName())) {
            Intent intent = new Intent(activity, AlertMessageActivity.class);
            intent.putExtra("filename", filename);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.push_top_translaterepeatin, 0);
        }

    }
}
