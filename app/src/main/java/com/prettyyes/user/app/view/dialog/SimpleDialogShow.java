package com.prettyyes.user.app.view.dialog;

import android.app.Activity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.prettyyes.user.R;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/20
 * Description: Nothing
 */
public class SimpleDialogShow {
    private SimpleDialong simpleDialog;
    private boolean hasCancel = true;

    public void dimissDialog() {
        if (null != simpleDialog && simpleDialog.isShowing()) {
            simpleDialog.dismiss();
        }
    }

    public void setHasCancel(boolean hasCancel) {
        this.hasCancel = hasCancel;
    }

    public void showDialog(Activity activity, View view) {
        if (!activity.isFinishing()) {
            if (null == simpleDialog) {
                simpleDialog = new SimpleDialong(activity, R.style.simple_dialog);
                simpleDialog.setCancelable(hasCancel);
                if (null != view) {
                    simpleDialog.setContentView(view);
                }
                WindowManager windowManager = (activity).getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = simpleDialog.getWindow().getAttributes();
                lp.width = (int) (display.getWidth() * 0.8); //设置宽度
                lp.alpha = 0.85f;      //设置本身透明度
                simpleDialog.getWindow().setAttributes(lp);
            }
            try {
                simpleDialog.show();
            } catch (Exception e) {
            }
        }
    }

}
