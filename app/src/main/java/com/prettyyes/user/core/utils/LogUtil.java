package com.prettyyes.user.core.utils;

import android.util.Log;

import com.prettyyes.user.AppConfig;

/**
 * @description 日志打印类
 */
public class LogUtil {

    /**
     * 日志打印Tag值
     */
   static  String logTag = "TAG";

    public static void i(String msg) {

        if (AppConfig.isDebug) {
            Log.i(logTag, msg);
        }
    }

    public static void i(String Tag, String msg) {

        if (AppConfig.isDebug) {
            Log.i(Tag, msg);
        }
    }

    public static void v(String msg) {
        if (AppConfig.isDebug) {
            Log.v(logTag, msg);
        }
    }

    public static void v(String Tag, String msg) {

        if (AppConfig.isDebug) {
            Log.v(Tag, msg);
        }
    }

    public static void d(String msg) {

        if (AppConfig.isDebug) {
            Log.d(logTag, msg);
        }
    }

    public static void d(String Tag, String msg) {

        if (AppConfig.isDebug) {
            Log.d(Tag, msg);
        }
    }

    public static void e(String msg) {

        if (AppConfig.isDebug) {
            Log.e(logTag, msg);
        }
    }

    public static void e(String Tag, String msg) {

        if (AppConfig.isDebug) {
            Log.e(Tag, msg);
        }
    }

}
