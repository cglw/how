package com.prettyyes.user.core;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.fragments.mianpage.QuestionFragment;
import com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.AppUtil;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_CATEGORY;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_HOME;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_MATCH;
import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_REWARD;

/**
 * Created by chengang on 2017/11/3.
 * 数据统计的封装
 */

public class AppStatistics {

    private static final String TAG = "AppStatistics";

    public static void onEvent(Context context, String event_id) {

        try {
            MobclickAgent.onEvent(context, event_id);
            String key = event_id;
            String value = "";
            String[] split = event_id.split(";");
            if (split.length == 2) {
                key = split[0];
                value = split[1];
            }
            onEvent(context, key, key, value);
        } catch (Exception ee) {

        }


    }


    public static void onEvent(Context context, String event_id, String key, String value) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(key, value);
            map.put("unique_id", AppUtil.getIdfa());
            TCAgent.onEvent(context, event_id, "", map);
        } catch (Exception ee) {

        }

    }

    public static void onEventDurtion(Context context, String event_id, String task_id, String durtion) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("task_id", task_id);
            map.put("duration", durtion);
            map.put("unique_id", AppUtil.getIdfa());
            TCAgent.onEvent(context, event_id, "", map);
        } catch (Exception ee) {

        }

    }

    //    统计首页不同页面进入的
    public static void onEventCommon(Context context, String event_id) {

        try {
            getEventHead();
            String key = "";
            String value = "";
            String[] split = event_id.split(";");
            if (split.length == 2) {
                key = split[0];
                value = split[1];
            }
            onEvent(context, EventHead + key, key, value);
            MobclickAgent.onEvent(context, EventHead + event_id);
        } catch (Exception ee) {

        }

    }

    public static void onPageStart(Context context, String event_id) {
        TCAgent.onPageStart(context, event_id);
        MobclickAgent.onPageStart(event_id);
    }

    public static void onPageEnd(Context context, String event_id) {
        TCAgent.onPageEnd(context, event_id);
        MobclickAgent.onPageEnd(event_id);

    }

    public static String EventHead = "home_";

    //获取首页的tab
    public static void getEventHead() {
        BaseActivity topActivity = (BaseActivity) ZBundleCore.getInstance().getTopActivity();
        if (topActivity.getClass().equals(MainActivity.class)) {
            Fragment fragment = ((MainActivity) topActivity).showHome();
            if (fragment != null) {
                QuestionVpFragment currentFragment = (QuestionVpFragment) ((QuestionFragment) fragment).getCurrentFragment();
                if (PAGE_HOME.equals(currentFragment.type)) {
                    EventHead = "home_";
                } else if (PAGE_CATEGORY.equals(currentFragment.type)) {
                    EventHead = "categoty_";
                } else if (PAGE_MATCH.equals(currentFragment.type)) {
                    EventHead = "new_task_";
                } else if (PAGE_REWARD.equals(currentFragment.type)) {
                    EventHead = "rewrad_";
                }


            }
        }
    }
}


