package com.prettyyes.user.core.containter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Project Name: testApkUpdate
 * Package Name: com.cg.app.main.containter
 * Author: SmileChen
 * Created on: 2016/9/14
 * Description: Nothing
 */
public class ZBundle {
    private Activity ui;// 界面
    private ZBundleCallback callback;// 界面回调
    private Bundle data;// 下一个组件需要的参数

    public ZBundle(Activity ui) {
        this.ui = ui;
    }

    public ZBundle(Activity ui, ZBundleCallback callback) {
        this.ui = ui;
        this.callback = callback;
    }

    public ZBundle(Activity ui, Bundle data) {
        this.ui = ui;
        this.data = data;
    }

    public ZBundle(Activity ui, Bundle data, ZBundleCallback callback) {
        this.ui = ui;
        this.callback = callback;
        this.data = data;
    }

    public Activity getUi() {
        return ui;
    }

    public void setUi(Activity ui) {
        this.ui = ui;
    }

    public ZBundleCallback getCallback() {
        return callback;
    }

    public void setCallback(ZBundleCallback callback) {
        this.callback = callback;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }


}
