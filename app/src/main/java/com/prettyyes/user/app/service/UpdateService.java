package com.prettyyes.user.app.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.NotificationCompat;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.ApiImpls.VersionApiImpl;
import com.prettyyes.user.api.netXutils.ProgressCallback;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.receiver.UpdateNotifyReceiver;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.LogUtil;

import java.io.File;
import java.io.IOException;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.service
 * Author: SmileChenx
 * Created on: 2016/9/1
 * Description: Nothing
 */
public class UpdateService extends Service {
    private static String down_url;
    private static final int DOWN_OK = 1; // 下载完成
    private static final int DOWN_ERROR = 0;
    private static final int DOWN_STOP = 3;
    private static final int DOWN_CONNTINUE = 4;
    private static final int DOWN_PROGRESS = 2;
    public static final String KEY_PERCENT = "percent";

    private String app_name;
    private NotificationManager manager;
    private Notification notification;
    private NotificationCompat.Builder builder;
    private PendingIntent pendingIntent;
    private PendingIntent updateIntent;
    private String updateFile;
    private int curPercent = 0;

    private int notification_id = 1000;
    long totalSize = 0;// 文件总大小
    /***
     * 更新UI
     */
    final Handler handler = new Handler() {
        @SuppressWarnings("deprecation")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_OK:
                    downloadComplete();
                    installApk(new File(updateFile));

                    break;
                case DOWN_ERROR:
                    downloadError();
                    break;
                case DOWN_PROGRESS:
                    updateProgress(msg);
                    break;
                default:
                    stopService();
                    break;
            }
        }
    };

    private void downloadComplete() {
        // 下载完成，点击安装
        Intent installApkIntent = getFileIntent(new File(updateFile));
        pendingIntent = PendingIntent.getActivity(UpdateService.this, 0, installApkIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent)
                .setContentText("下载成功，点击安装")
                .setTicker("下载成功")
                .setAutoCancel(true);
        notification = builder.build();
        manager.notify(notification_id, notification);
    }

    private void downloadError() {
        isDownloading = false;
        builder.setTicker("下载失败")
                .setContentText("下载失败，点击重试")
                .setAutoCancel(true)
                .setContentIntent(getClick_PendingIntent()).setDeleteIntent(getDelete_PendingIntent());
        notification = builder.build();
        manager.notify(notification_id, notification);
    }

    private void stopService() {
        stopService(new Intent(this, UpdateService.class));
    }


    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }

    private MyReceiver myReceive;

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constant.DownloadAPk.equals(intent.getAction())) {
                isDownloading = intent.getBooleanExtra("isDownloading", false);
                if (builder == null)
                    return;

                if (!isDownloading) {

                    builder.setContentText(curPercent + "%" + "     点击继续下载")
                            .setTicker("暂停下载")
                            .setDeleteIntent(getDelete_PendingIntent())
                            .setContentIntent(getClick_PendingIntent());
                    notification = builder.build();
                    manager.notify(notification_id, notification);

                } else {
                    builder.setContentText(curPercent + "%" + "     点击暂停下载")
                            .setTicker("恢复下载")
                            .setContentIntent(getClick_PendingIntent());
                    notification = builder.build();
                    manager.notify(notification_id, notification);
                    try {
                        downloadUpdateFile(updateFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerBorcast();
        if (intent != null) {
            try {
                app_name = getString(R.string.app_name);
                down_url = AppConfig.APK_DOWNLOAD_URL;
                initNotification();
                File updateFile = FileUtils.getDiskCacheDir(getApplicationContext(), "prettyyes.apk");
                if (!updateFile.exists()) {
                    try {
                        updateFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                downloadUpdateFile(updateFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return START_STICKY;
    }

    private void registerBorcast() {
        myReceive = new MyReceiver();
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction(Constant.DownloadAPk);
        this.registerReceiver(myReceive, localIntentFilter);
    }

    /***
     * 下载文件
     */
    public void downloadUpdateFile(String file) throws Exception {
        isDownloading = true;
        updateFile = file;
        new VersionApiImpl().downLoadFile(file, new ProgressCallback() {
            @Override
            public void onFail(String paramString1, String paramString2) {
                Message message = handler.obtainMessage();
                message.what = DOWN_ERROR;
                handler.sendMessage(message);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Message msg = new Message();
                msg.what = DOWN_PROGRESS;
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_PERCENT, (int) (100 * current / total));
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String result) {
                Message message = handler.obtainMessage();
                message.what = DOWN_OK;
                handler.sendMessage(message);
                stopService();
            }
        });
    }

//    // 下载完成后打开安装apk界面
//    public static void installApk(File file, Context context) {
//        //L.i("msg", "版本更新获取sd卡的安装包的路径=" + file.getAbsolutePath());
//        Intent openFile = getFileIntent(file);
//        context.startActivity(openFile);
//    }

    public  void installApk(File file) {
        this.startActivity(getFileIntent(file));
    }

    public  Intent getFileIntent(File file) {
        if (file == null) {
            AppUtil.showToastShort("文件不存在");
        }
        Uri uri = Uri.fromFile(file);
        String type = getMIMEType(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= 24) {

            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//增加读写权限
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//增加读写权限
            intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
            intent.setDataAndType(FileProvider.getUriForFile(this,getPackageName() + getString(R.string.rc_fileprovider), file),
                    "application/vnd.android.package-archive");
        }else {
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setDataAndType(uri, type);
        }
        return intent;
    }

    /**
     * 初始化消息通知
     */
    public void initNotification() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        long when = System.currentTimeMillis();
        builder = new NotificationCompat.Builder(this);
        // Ticker是状态栏显示的提示
        builder.setTicker("应用更新")
                .setContentTitle(app_name)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(when)
                .setContentText("准备下载")
                .setProgress(100, 0, false);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        manager.notify(notification_id, notification);
    }

    public static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length());
        if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            type = "*/*";
        }
        return type;
    }


    /**
     * 更新下载进度
     *
     * @param msg
     */
    private void updateProgress(Message msg) {
        int percentage = msg.getData().getInt(KEY_PERCENT);
        curPercent = percentage;
        builder.setProgress(100, percentage, false)
                .setTicker(null)
                .setContentText(percentage + "%" + "     点击暂停下载")
                .setAutoCancel(false)
                .setContentIntent(getClick_PendingIntent());
        notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        manager.notify(notification_id, notification);

    }

    private PendingIntent getClick_PendingIntent() {
        Intent intent = new Intent(this, UpdateNotifyReceiver.class);
        intent.setAction(Constant.DownloadAPkNotifyClick);
        intent.putExtra("isDownloading", isDownloading);
        intent.putExtra("url", down_url);
        return PendingIntent.getBroadcast(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private PendingIntent getDelete_PendingIntent() {
        Intent intentCancel = new Intent(UpdateService.this, UpdateNotifyReceiver.class);
        intentCancel.setAction(Constant.NotifyDelete);
        return PendingIntent.getBroadcast(UpdateService.this, 0, intentCancel, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(myReceive);
        if (handler != null) {
            handler.removeMessages(0);
            handler.removeMessages(1);
            handler.removeMessages(2);
        }
    }

    private static boolean isDownloading = true;


}