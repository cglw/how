package com.prettyyes.user.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.VersionApiImpl;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.api.netXutils.urls.VersionUrl;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.service.UpdateService;
import com.prettyyes.user.app.view.dialog.UpdateDialog;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.VersionModel;

import static com.prettyyes.user.AppConfig.UPDATE_REMIND_TIME;
import static com.prettyyes.user.AppConfig.UPDATE_REMIND_TIME_TEST;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/9/1
 * Description: app更新
 */
public class UpdateHandler {
    private static final String TAG = "UpdateHandler";
    private static final String VERSION_KEY = "VERSION_KEY";
    private Activity context;
    private AlertView alertView;
    private boolean mustShow = false;


    public UpdateHandler(Activity context) {
        this.context = context;
    }

    public UpdateHandler(Activity context, boolean mustShow) {
        this.context = context;
        this.mustShow = mustShow;
    }

    public AlertView  checkVersion() {

        new VersionApiImpl().getVersion(new NetReqCallback<VersionModel>() {
            @Override
            public void apiRequestFail(String code,String method) {
            }


            @Override
            public void apiRequestSuccess(VersionModel apiResponse,String method) {
                VersionModel version_load = apiResponse;
                AppConfig.APK_DOWNLOAD_URL = version_load.getDownloadUrl();

                //需要更新的话 showDownload()方法会每次吧最新的请求结果存储，时间 为当前时间
                if (isneedUpdate(context, version_load.getVersion())) {

                    //设置里面，更新必须弹出提醒更新
                    if (mustShow) {
                        showDownload(version_load);
                    } else {
                        //先获取本地存储的信息
                        VersionModel version_local = SpMananger.getStorageProxy().resolve(VERSION_KEY, VersionModel.class);
                        //本地如果没有存储过,直接弹出提醒更新
                        if (version_local == null || version_local.getTime() == 0) {
                            showDownload(version_load);
                        } else {
                            //如果本地存储的比请求的版本还要低，弹出提醒更新
                            if (isneedSave(version_local.getVersion(), version_load.getVersion())) {
                                showDownload(version_load);
                            } else {
                                //本地存储的信息跟请求的一样 获取本地存储版本时间，以及当前时间
                                long now = System.currentTimeMillis();
                                long local_save = version_local.getTime();

                                //dtime 间隔时间
                                long dtime = 0;
                                if (AppConfig.isDebug)
                                    dtime = UPDATE_REMIND_TIME_TEST;
                                else
                                    dtime = UPDATE_REMIND_TIME;

                                //如果当前时间减去本地存储版本的时间，大于间隔时间，弹出提醒更新
                                if ((now - local_save) > dtime) {
                                    showDownload(version_load);

                                }
                            }

                        }
                    }



                } else {
                    if (mustShow)
                        AppUtil.showToastShort("已是最新版本");
                }
            }
        });

        return alertView;
    }

    private void initAlertView(String content) {
        if (content == null || content.isEmpty()) {
            content = "为了更好的用户体验，\n" +
                    "我们为您更新了一个新的版本";
        }
        alertView = new AlertView("更新提醒", content, "稍后更新", new String[]{"直接下载"}, null, context, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, final int position) {
                if (position == 0) {
                    downLoadAppByServer();
                }
            }
        }).setCancelable(false);
    }

    private void showDownload(VersionModel versionModel) {
        versionModel.setTime(System.currentTimeMillis());
        SpMananger.getStorageProxy().save(VERSION_KEY, versionModel);
        new UpdateDialog(context).setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog tag = (UpdateDialog) v.getTag();
                if (tag != null)
                    tag.dismiss();
                downLoadAppByServer();

            }
        }).setUpdateContent(versionModel.getContent()).show();


    }

    private void downLoadAppByServer() {
        AppUtil.showToastShort("开始下载");
        Intent updateIntent = new Intent(context, UpdateService.class);
        context.startService(updateIntent);
    }

    private void initNocanDisAlertView(String content) {
        if (content == null || content.isEmpty()) {
            content = "您的版本过低,需要强制更新";
        }
        alertView = new AlertView("更新提醒", content, "残忍拒绝", new String[]{"直接下载"}, null, context, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, final int position) {
                if (position == -1) {
                    AppUtil.showToastShort("需要更新后才可以使用");
                    // 杀死该应用进程
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                } else if (position == 0) {
                    downLoadAppByServer();

                }
            }
        }).setCancelable(false);
    }

    public static boolean isneedSave(String currentversion, String newversion) {
        String[] oldVersionSegs = currentversion.split("\\.");
        String[] newVersionSegs = newversion.split("\\.");
        for (int i = 0; i < newVersionSegs.length; i++) {
            String oldVersionSeg = "-1";
            String newVersionSeg = newVersionSegs[i];
            if (oldVersionSegs.length > i) {
                oldVersionSeg = oldVersionSegs[i];
            }
            int oldVersion = Integer.parseInt(oldVersionSeg);
            int newVersion = Integer.parseInt(newVersionSeg);
            if (oldVersion < 0 || newVersion < 0)
                continue;
            if (newVersion > oldVersion)
                return true;
            if (newVersion < oldVersion) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkNeedUpdateByLocal() {
        VersionModel saveVersion = SpMananger.getStorageProxy().resolve(VERSION_KEY, VersionModel.class);
        if (saveVersion != null) {
            return isneedUpdate(BaseApplication.getAppContext(), saveVersion.getVersion());
        }
        return false;

    }

    public static boolean isneedUpdate(Context context, String newVersionString) {
        if (newVersionString == null) {
            return false;
        }
        String oldVersionString = getVersion(context);
        String[] oldVersionSegs = oldVersionString.split("\\.");
        String[] newVersionSegs = newVersionString.split("\\.");
        for (int i = 0; i < newVersionSegs.length; i++) {
            String oldVersionSeg = "-1";
            String newVersionSeg = newVersionSegs[i];
            if (oldVersionSegs.length > i) {
                oldVersionSeg = oldVersionSegs[i];
            }
            int oldVersion = Integer.parseInt(oldVersionSeg);
            int newVersion = Integer.parseInt(newVersionSeg);
            if (oldVersion < 0 || newVersion < 0)
                continue;
            if (newVersion > oldVersion)
                return true;
            if (newVersion < oldVersion) {
                return false;
            }
        }
        return false;
    }

    public static boolean isneedUpdateByVersionCode(Context context, int version_code) {

        int old_version_code = getAppVersionCode(context);
        if (version_code > old_version_code)
            return true;
        return false;
    }


    public static String getVersion(Context context) {
        PackageManager manager;
        PackageInfo info = null;
        manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Integer getAppVersionCode(Context context) {
        PackageManager manager;
        PackageInfo info = null;
        manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void cancle() {
        HttpAccess.getInstance().cancelDownLoad(VersionUrl.Url_version);
    }


}
