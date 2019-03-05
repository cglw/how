package com.prettyyes.user.core.containter;

import android.app.Activity;

import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.app.ui.common.WebviewActivity;

import java.util.ArrayList;

/**
 * Project Name: testApkUpdate
 * Package Name: com.cg.app.main.containter
 * Author: SmileChen
 * Created on: 2016/9/14
 * Description: Nothing
 */
public class ZBundleConfig {
    public static ArrayList<Class<? extends Activity>> bundleMap = new ArrayList();

    static {
        bundleMap.add(MainActivity.class);
        bundleMap.add(WebviewActivity.class);
        //   bundleMap.add(KolDetailActivity.class);
//        bundleMap.add(PayActivity.class);
    }

    public static final int RESULT_OK = 0x0;// 组件业务执行成功
    public static final int RESULT_FAILED = 0x1;// 组件业务执行失败
    public static final int RESULT_VISIABEL = 0x2;// 组件业务执行失败
    public static final int RESULT_INVISIABLE = 0x3;// 组件业务执行失败
}
