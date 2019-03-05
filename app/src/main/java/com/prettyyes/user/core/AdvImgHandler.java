package com.prettyyes.user.core;

import android.content.Context;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.SystemApiImpl;
import com.prettyyes.user.api.netXutils.ApiImpls.VersionApiImpl;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.model.common.AppBanner;

import java.io.File;
import java.io.IOException;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/9/20
 * Description: 闪屏页的广告 每次请求缓存判断时间
 */
public class AdvImgHandler {
    private static final String TAG = "AdvImgHandler";

    public AdvImgHandler() {
    }

    private String loadurl = "";

    public void cancle() {
        HttpAccess.getInstance().cancelDownLoad(loadurl);
    }

    public void loadAdv(final Context context) {

        new SystemApiImpl().getBanner("start_page", new NetReqCallback<AppBanner>() {
            @Override
            public void apiRequestFail(String message,String method) {

            }

            @Override
            public void apiRequestSuccess(AppBanner appBanner,String method) {
                if (appBanner.getList().size() <= 0) {
                    return;
                }
                File updateFile = FileUtils.getDiskCacheDir(BaseApplication.getAppContext(), appBanner.getList().get(0).getImg_url());
                new SPUtils<AppBanner.ListEntity>().save(appBanner.getList().get(0), "start_page");

                if (!updateFile.exists()) {
                    try {
                        updateFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 * 已经下载了
                 */
                if (updateFile.length() > 0) {
                    return;
                }
                loadurl = appBanner.getList().get(0).getImg_url();
                new VersionApiImpl().downLoadImg(loadurl, updateFile.getAbsolutePath(), new ProgressCallback() {
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

                    }
                });
            }
        });

    }

}
