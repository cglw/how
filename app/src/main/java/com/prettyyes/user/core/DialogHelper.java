package com.prettyyes.user.core;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.containter.ZBundleCore;


/**
 * Created by chengang on 2017/5/22.
 */

public class DialogHelper {


    private static DialogHelper dialogHelper;

    private DialogHelper() {

    }

    public static DialogHelper getInstance() {
        if (dialogHelper == null) {
            synchronized (DialogHelper.class) {
                if (dialogHelper == null)
                    dialogHelper = new DialogHelper();
            }
        }
        return dialogHelper;
    }


    public void showDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZBundleCore.getInstance().getTopActivity());
        builder.setMessage(title)
                .setPositiveButton("确定", null)
                .setTitle("提醒")
                .show();
    }

    public AlertDialog.Builder getDialogNoCancel(String content, DialogInterface.OnClickListener confirm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZBundleCore.getInstance().getTopActivity());
        builder.setMessage(content)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", confirm)

                .setTitle("提醒")
                .setCancelable(false);

        return builder;
    }

    public AlertDialog.Builder getDialogListNoCancel(String[] items, DialogInterface.OnClickListener confirm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZBundleCore.getInstance().getTopActivity());
        builder.setItems(items, confirm);
        return builder;
    }


    public AlertDialog.Builder getDialogNoCancel(String content, String confirm_tv, DialogInterface.OnClickListener confirm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZBundleCore.getInstance().getTopActivity());
        builder.setMessage(content)
                .setNegativeButton("取消", null)
                .setPositiveButton(confirm_tv, confirm)
                .setTitle("提醒")
                .setCancelable(false);

        return builder;
    }

    public AlertDialog.Builder getDialogNoCancel(int res, DialogInterface.OnClickListener confirm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZBundleCore.getInstance().getTopActivity());
        builder.setMessage(BaseApplication.getAppContext().getString(res))
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", confirm)

                .setTitle("提醒")
                .setCancelable(false);

        return builder;
    }

}
