package com.prettyyes.user.app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/7/20
 * Description: Nothing
 */
public class SimpleDialong extends Dialog {


    public SimpleDialong(Context context) {
        super(context);
    }

    public SimpleDialong(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SimpleDialong(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
