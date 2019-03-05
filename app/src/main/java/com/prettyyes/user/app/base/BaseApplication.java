package com.prettyyes.user.app.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

import com.meituan.android.walle.WalleChannelReader;
import com.microquation.linkedme.android.LinkedME;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.app.service.InitializeService;
import com.prettyyes.user.app.ui.other.MiddleActivity;
import com.prettyyes.user.core.MyLifecycleHandler;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.ronyun.activity.RongyunUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.tendcloud.tenddata.TCAgent;

import org.xutils.x;

import io.rong.imkit.RongIM;

import static com.prettyyes.user.core.utils.ImageLoadUtils.extral;
import static com.prettyyes.user.core.utils.ImageLoadUtils.extral_head;

/**
 * Created by Administrator on 2016/7/1.
 */
public class BaseApplication extends Application {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static final String TAG = "BaseApplication";
    public static Context context;
    public static BaseApplication instance;
    public static String UUID = null;
    public static String USER_CHAT_ID = null;
    public static String APP_CHANNEL = null;
    public static UserInfo userInfo;
    public static float ScreenDensity;//密度系数
    public static int ScreenWidth;//屏幕宽度
    public static int ScreenHeight;//屏幕高度
    public static Typeface TYPE_MONEY;

    public static BaseApplication getInstance() {
        if (instance == null)
            instance = new BaseApplication();
        return instance;
    }

    private void initSetting() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScreenDensity = dm.density;//密度系数
        ScreenWidth = dm.widthPixels;//屏幕的宽度
        ScreenHeight = dm.heightPixels;
        extral = String.format("?imageView2/4/w/%d/h/%d", ScreenWidth / 4, ScreenWidth / 4);
        extral_head = String.format("?imageView2/4/w/%d/h/%d", 150, 150);
        TYPE_MONEY = Typeface.createFromAsset(context.getAssets(), "Athelas_W01_Italic.ttf");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (getApplicationInfo().packageName.equals(AppUtil.getCurProcessName(getApplicationContext()))) {
            context = getApplicationContext();
            initAccount();
            initSetting();
            x.Ext.init(this);
            InitializeService.start(this);
            initLinkedMe();
            initX5WebCore();
            initTC();
            registerActivityLifecycleCallbacks(new MyLifecycleHandler());
            initRongyun();
            LogUtil.i("Application" + AppUtil.getCurProcessName(getApplicationContext()));

        } else {
            LogUtil.i("Application" + AppUtil.getCurProcessName(getApplicationContext()));

        }

    }
    // 初始化talkingdata
    private void initTC() {
        TCAgent.LOG_ON = AppConfig.isDebug;
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        TCAgent.init(this, AppConfig.TD_APPID, channel);
        TCAgent.setReportUncaughtExceptions(true);
    }

    // 初始化个人信息
    private void initAccount() {
        SpMananger.getUserInfo();
    }

    public static Context getAppContext() {
        return context;
    }

    //初始化linkedme
    private void initLinkedMe() {
        try {
            if (AppConfig.isDebug) {
                LinkedME.getInstance(this).setDebug();
            } else {
                LinkedME.getInstance(this);

            }
            LinkedME.getInstance().setHandleActivity(MiddleActivity.class.getName());

        } catch (Exception ee) {
            ee.printStackTrace();

        }

    }


    //初始化X5浏览器内核
    private void initX5WebCore() {
        if (!QbSdk.isTbsCoreInited()) {
            QbSdk.preInit(getApplicationContext(), new QbSdk.PreInitCallback() {
                @Override
                public void onCoreInitFinished() {

                }

                @Override
                public void onViewInitFinished(boolean b) {

                }
            });// 设置X5初始化完成的回调接口
        }


        //  预加载X5内核
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            //初始化完成回调

        }

        @Override
        public void onCoreInitFinished() {

        }
    };

    //初始化融云
    private void initRongyun() {
        if (AppConfig.isTest) {
            RongIM.init(this, "mgb7ka1nbjtkg");
        } else {
            RongIM.init(this, "8w7jv4qb71hvy");
        }
        if (SpMananger.getUserInfo() != null && SpMananger.getUserInfo().rongyun_token != null) {
            RongyunUtils.connect(SpMananger.getUserInfo().rongyun_token.getRongyun_buyer(), getAppContext(), new RongyunUtils.CallBackUid() {
                @Override
                public void back(String userid) {

                }
            });

        }
    }
}
