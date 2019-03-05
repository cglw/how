package com.prettyyes.user.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.fragments.splash.AdvFragment;
import com.prettyyes.user.app.fragments.splash.GuideFragment;
import com.prettyyes.user.core.AdvImgHandler;
import com.prettyyes.user.core.SPUtils;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.model.common.AppBanner;

import java.io.File;

import static com.prettyyes.user.AppConfig.STARTBAR_ALPHA;

public class SplashActivity extends AppCompatActivity {


    private AdvImgHandler advImgHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, STARTBAR_ALPHA, null);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        loadAdvImg();

        if (AccountDataRepo.getAccountManager().isFirstUse())
            nextMain();
        else
            loadLayout();

    }

    private void loadGuide() {
        loadfragment(new GuideFragment());
    }


    @Override
    protected void onStart() {
        super.onStart();
        TimeManager.getManager().syncServerTime();
        if (ZBundleCore.getInstance().isExist(MainActivity.class)) {
            this.finish();
        }

    }

    private void loadAdvImg() {
        advImgHandler = new AdvImgHandler();
        advImgHandler.loadAdv(this);
    }

    /**
     * 满足有本地信息，有图片链接，在限定时间内，有图片
     */
    private void loadLayout() {
        if (!new SPUtils<AppBanner.ListEntity>().isEixst(AppBanner.ListEntity.class, "start_page")) {
            nextMain();
        } else {
            AppBanner.ListEntity banner = new SPUtils<AppBanner.ListEntity>().get(AppBanner.ListEntity.class, "start_page");
            if (banner == null) {
                nextMain();
                return;
            }
            try {
                File updateFile = FileUtils.getDiskCacheDir(this, banner.getImg_url());

                if (updateFile.exists() && banner.getIs_open().equals("1") && updateFile.length() > 0 &&
                        banner.getBanner_rule().length() > 0 && FormatUtils.longtimeToDate(banner.getEnd_time()).getTime() > System.currentTimeMillis()
                        ) {
                    loadfragment(new AdvFragment());
                } else {
                    nextMain();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout_splash, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (advImgHandler != null) {
            advImgHandler.cancle();
            advImgHandler = null;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(Constant.ACTIVITY_PAUSE);
        sendBroadcast(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(Constant.ACTIVITY_ONRESUM);
        sendBroadcast(intent);
        AccountDataRepo.getAccountManager().refreshUseInfoLocal();
    }

    private void nextMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
