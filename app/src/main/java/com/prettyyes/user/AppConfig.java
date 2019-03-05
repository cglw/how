package com.prettyyes.user;

import android.os.Environment;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user
 * Author: SmileChen
 * Created on: 2016/7/19
 * Description: Nothing
 */
public class AppConfig {
    public static String getUrl() {
        if (isTest) {
            return TestBaseUrl;
        } else {
            return BaseUrl;
        }
    }

    public static final String PAPER_ADD_UDL = "http://test.prettyyes.com/h5editor/papereditor";
    public static final boolean isOpenWeinxinPay = true;
    //开启debug可以看到日志
    public static boolean isDebug = false;
    //正是测试服务器配置
    public static boolean isTest = true;
    public static boolean REGISTER_DIALOG_OPEN = false;
    //socket 地址
    public static String SOCKET_ADDRESS = "ws://api.prettyyes.com:7272";
    public static final String UUID_TEST = "acee7320ee974cad52c5ae2c84fe5a4a";
    public static String APK_DOWNLOAD_URL = "https://img.prettyyes.com/app-release.apk";
    public static final String TestBaseUrl = "http://test.prettyyes.com";
    public static final String BaseUrl = "https://api.prettyyes.com";
    //加密的key
    public static final String DesStringKey = "gcdata";
    public static final String KF_HEAD_IMG = "http://img.prettyyes.com/Kefu.png";
    //服务器版本
    public static final String ServerVersion = "application/pretty.yes.v8+json";
    public static final String APPID_WeiXin = "wx2297246989e7f80e";
    //保存文件地址
    public static final String LocalImages = Environment.getExternalStorageDirectory() + "/how/";
    public static final String LocalImages_cache =LocalImages+ "cache/";
    public static final int CONNECT_TIMEOUT = 15 * 1000;
    //上传普通文件超时
    public static final int CONNECT_TIMEOUT_FIEL = 30 * 1000;
    //上传video 超时
    public static final int CONNECT_TIMEOUT_VIDEO_FIEL = 90 * 1000;
    //下载文件超时
    public static final int CONNECT_TIMEOUT_FIEL_DOWN = 60*60 * 1000;
    //每隔多少天提醒一次更新
    public static final int UPDATE_REMIND_TIME = 1000 * 60 * 60 * 24 * 7;
    public static final int UPDATE_REMIND_TIME_TEST = 10 * 1000 * 60;
    //    STARTBAR 透明值
    public static final int STARTBAR_ALPHA = 50;
    public static final String KF = "KEFU146718733598596";
    public static final long UNREGISTER_SHOW = 10 * 60;
    public static String CLIENT_ID = "";
    public static final int MIN_PAGE_SIZE = 10;
    public static final int PAGE_LIMIT = 4;
    public static final int DRIVID_HEIGHT_SMALL = 8;
    public static final int DRIVID_HEIGHT_NORMAL = 16;
    public static final double DRIVID_HEIGHT_LINE = 0.5;
    public static final String PARTNER_AGREE = "http://how.partner.prettyyes.com/partner/agree";
    public static final String HOW_RULE = "http://test.prettyyes.com/howscoredetail";
    public static final String TD_APPID = "A76DCD76235B438CB5B7C913B52795A9";


}
