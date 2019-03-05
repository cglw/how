package com.prettyyes.user.core;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by chengang on 2017/6/25.
 */

public class AlertDialogManager {

    private AlertDialogManager() {

    }

    public static AlertDialogManager getInstance() {
        return AlertDialogManagerInstance.singleInstance;
    }

    private static class AlertDialogManagerInstance {
        static AlertDialogManager singleInstance = new AlertDialogManager();
    }

    public void show(Activity activity, String title, DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(title)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", onClickListener)
                .setTitle("提醒")
                .show();
    }

    public void show(Activity activity, String title, DialogInterface.OnClickListener confirm, DialogInterface.OnClickListener cancel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(title)
                .setNegativeButton("取消", cancel)
                .setPositiveButton("确定", confirm)
                .setTitle("提醒")
                .show();
    }

    public void show(Activity activity, String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(content)
                .setTitle(title)
                .show();
    }

    public void show(Activity activity, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(content)
                .setTitle("提醒")
                .show();
    }
}
