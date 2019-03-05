package com.prettyyes.user.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.prettyyes.user.AppConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by chengang on 2017/8/4.
 */

public class InitializeService extends IntentService {
    private static final String TAG = "InitializeService";
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.anly.githubapp.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        initSdk();
    }

    public static RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        return refWatcher;
    }

    private void initSdk() {

            initLeakCanary();
//            initX5WebCore();
            initMobclickAgent();
//            initRongyun();
            initSocket();




    }


    private void initLeakCanary() {
        refWatcher = LeakCanary.install(this.getApplication());

    }

    private void initMobclickAgent() {
        MobclickAgent.setDebugMode(AppConfig.isDebug);
//        PlatformConfig.setWeixin("wx2297246989e7f80e", "afe429a5691f4cd2d763eaad7b538b2f");
        PlatformConfig.setWeixin("wx2297246989e7f80e", "94957e3bb132c2c169e1d4ab5be92d20");
        PlatformConfig.setSinaWeibo("4160932024", "e171eebe1a5c755b56733affd6a7ba94","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1105140900", "7eSorHG5L5TTHXEj");
        UMShareAPI.init(this,"5796c89b67e58e2d8f00201b");


    }

    private void initSocket() {
        if (AppConfig.isTest) {
            AppConfig.SOCKET_ADDRESS = "ws://test.prettyyes.com:7272";
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
