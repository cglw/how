package com.prettyyes.user.app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.igexin.sdk.PushManager;
import com.prettyyes.user.R;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.fragments.TestFragment;
import com.prettyyes.user.app.fragments.mianpage.KolListFragment;
import com.prettyyes.user.app.fragments.mianpage.MineV8Fragment;
import com.prettyyes.user.app.fragments.mianpage.MyQuestionFragment;
import com.prettyyes.user.app.fragments.mianpage.QuestionFragment;
import com.prettyyes.user.app.mvpPresenter.MainActivityPresenter;
import com.prettyyes.user.app.mvpView.MainActivityView;
import com.prettyyes.user.app.service.GetuiIntentService;
import com.prettyyes.user.app.service.GetuiPushService;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.view.component.GuideJumpComponent;
import com.prettyyes.user.app.view.component.GuideOneComponent;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AskUnreadEvent;
import com.prettyyes.user.core.event.CommentUnreadEvent;
import com.prettyyes.user.core.event.GuideOneDismissEvent;
import com.prettyyes.user.core.event.HowNewTask;
import com.prettyyes.user.core.event.InvateMeUnreadEvent;
import com.prettyyes.user.core.event.MineDynamicUnreadEvent;
import com.prettyyes.user.core.event.MorePageUnreadEvent;
import com.prettyyes.user.core.event.PostAskSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.FragmentTabUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.data.DataCenter;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

//import com.prettyyes.user.app.service.DemoPushService;

public class MainActivity extends BaseActivity implements MainActivityView {


    private String TAG = "MainActivity";
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<String> flags = new ArrayList<>();
    private int pageIndex = 0;//初始的pageIndex
    private QuestionFragment homeFragment;
    private KolListFragment messageFragment;
    private MyQuestionFragment myProblemFragment;
    private MineV8Fragment mineFragment;
    private TestFragment testFragment;
    private FragmentTabUtils tabUtils;

    @ViewInject(R.id.img_ask)
    ImageView img_ask;


    private Class userPushService = GetuiPushService.class;

    @ViewInject(R.id.ll_tips)
    private View ll_tips;
    @ViewInject(R.id.btn_five)
    private View btn_five;
    @ViewInject(R.id.main_radioGroups)
    private RadioGroup mMain_radioGroups;
    private MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AccountDataRepo.getAccountManager().isFirstUse()) {

            findViewById(R.id.img_guide).post(new Runnable() {
                @Override
                public void run() {
                    showGuideView(findViewById(R.id.img_guide));
                }
            });

        }
        mainActivityPresenter.checkVersion();//检查版本

        mainActivityPresenter.startShowNeedRegister();
        PushManager.getInstance().initialize(this.getApplicationContext(), userPushService);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetuiIntentService.class);
        if (!PushManager.getInstance().isPushTurnedOn(BaseApplication.getAppContext())) {
            PushManager.getInstance().turnOnPush(BaseApplication.getAppContext());
        }


    }


    @Override
    protected void setListener() {
        super.setListener();
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AskUnreadEvent>() {
            @Override
            protected void back(AskUnreadEvent obj) {
                mainActivityPresenter.getTabFourTips();

            }
        }, new RxCallback<CommentUnreadEvent>() {
            @Override
            protected void back(CommentUnreadEvent obj) {
                mainActivityPresenter.getTabFourTips();

            }
        }, new RxCallback<InvateMeUnreadEvent>() {
            @Override
            protected void back(InvateMeUnreadEvent obj) {
                mainActivityPresenter.getTabFiveTips();

            }
        }, new RxCallback<MineDynamicUnreadEvent>() {
            @Override
            protected void back(MineDynamicUnreadEvent obj) {
                mainActivityPresenter.setBadgeViewDynamic(obj.getNum());

            }
        }, new RxCallback<MorePageUnreadEvent>() {
            @Override
            protected void back(MorePageUnreadEvent obj) {
                mainActivityPresenter.setBadgeViewMore(obj.getNum());

            }
        }, new RxCallback<PostAskSuccessEvent>() {
            @Override
            protected void back(PostAskSuccessEvent obj) {
                mainActivityPresenter.selectIndexDynamic();

            }
        });
        img_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!JumpPageManager.getManager().checkUnlogin(getThis())) {
                    AskActivity.goAskActivity(getThis());
                }
            }
        });

    }


    @Override
    public void setStatusBar() {
        setTranslucenBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (homeFragment != null)
            DataCenter.getUnread(DataCenter.UnreadType.ALL);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initViews() {
        hideHeader();
    }


    @Override
    protected void loadData() {
        initFragment();
        initFragmentsArray();
        tabUtils = new FragmentTabUtils(pageIndex, getSupportFragmentManager(), fragments, R.id.index_container, mMain_radioGroups, null, flags, this);
        mainActivityPresenter.loadAlertSplash();//加载广告页
    }

    private void initFragmentsArray() {
        fragments.add(homeFragment);
        fragments.add(messageFragment);
        fragments.add(testFragment);
        fragments.add(myProblemFragment);
        fragments.add(mineFragment);
        flags.add("homeFragment");
        flags.add("messageFragment");
        flags.add("testFragment");
        flags.add("MyQuestionFragment");
        flags.add("mineFragment");
    }

    private void initFragment() {
        homeFragment = new QuestionFragment();
        messageFragment = new KolListFragment();
        myProblemFragment = new MyQuestionFragment();
        mineFragment = new MineV8Fragment();
        testFragment = new TestFragment();
    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        super.setInentFliter(inentFliter);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        if (isLoginRefresh(intent)) {
            tabUtils.checkIndex(0);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (alertView != null && alertView.isShowing() && alertView.getTag() == 1) {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public static void goMainActivity(Context c, int index) {
        Intent intent = new Intent(c, MainActivity.class);
        intent.putExtra("pageIndex", index);
        c.startActivity(intent);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (getIntent().getIntExtra("pageIndex", 0) <= 4) {
            pageIndex = getIntent().getIntExtra("pageIndex", 0);
            mainActivityPresenter.checkGroupIndex(pageIndex);
        }
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        if (getIntent().getIntExtra("pageIndex", 0) <= 4)
            pageIndex = getIntent().getIntExtra("pageIndex", 0);
    }

    @Override
    public void showFailedError(String tv) {
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public View getTipsView() {
        return ll_tips;
    }

    @Override
    public View getTipsMoreView() {
        return btn_five;
    }

    @Override
    public RadioGroup getRadioGroup() {
        return mMain_radioGroups;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainActivityPresenter != null)
            mainActivityPresenter.release_res();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppRxBus.getInstance().post(new HowNewTask(false));

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public Fragment showHome() {
        if (homeFragment != null && homeFragment.isVisible()) {
            return homeFragment;
        }
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    Guide guide;

    public void showGuideView(View targetView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setAlpha(150)
                .setHighTargetCorner(AppUtil.dip2px(40))
                .setHighTargetPadding(0)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                AppRxBus.getInstance().post(new GuideOneDismissEvent());

            }
        });


        builder.addComponent(new GuideOneComponent());
        builder.addComponent(new GuideJumpComponent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDataRepo.getAccountManager().UseApp();
                if (guide != null)
                    guide.dismissNoCallback();
            }
        }));
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show(this);

    }


}
