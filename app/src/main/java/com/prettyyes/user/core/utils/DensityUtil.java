package com.prettyyes.user.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;

import com.prettyyes.user.app.base.BaseApplication;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/7/29
 * Description: Nothing
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = BaseApplication.ScreenDensity;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = BaseApplication.ScreenDensity;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float dip2px_float(float dpValue) {
        final float scale = BaseApplication.ScreenDensity;
        return dpValue * scale;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float dp2px(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue * scale;
    }

    public static int getViewHeight(Activity context, View v) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        v.measure(size.x, size.y);
        int height = v.getMeasuredHeight();
        return height;
    }
}
