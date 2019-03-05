package com.prettyyes.user.app.ui.how;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.KolActPresenter;
import com.prettyyes.user.app.mvpView.KolActivityView;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AppTimeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;

import org.xutils.view.annotation.ViewInject;

public class KolActivityActivity extends BaseActivity implements KolActivityView {


    @ViewInject(R.id.tv_kolactAct_actname)
    private TypefaceTextView tv_actname;
    @ViewInject(R.id.tv_kolactAct_day)
    private TextView tv_day;
    @ViewInject(R.id.tv_kolactAct_month)
    private TextView tv_month;
    @ViewInject(R.id.tv_kolactAct_weekandtime)
    private TextView tv_weekandtime;
    @ViewInject(R.id.tv_kolactAct_detailtime)
    private TextView tv_detailtime;
    @ViewInject(R.id.tv_kolactAct_activitydesc)
    private TypefaceTextView tv_activitydesc;
    @ViewInject(R.id.img_kolactAct_calendar)
    private ImageView img_calendar;
    @ViewInject(R.id.checkbox_kolactAct_collect)
    private CheckBox checkbox_collect;
    @ViewInject(R.id.img_kolactAct_share)
    private ImageView img_share;
    @ViewInject(R.id.vp_kolactAct_kol)
    private AutoViewPager autoViewPager_kol;
    @ViewInject(R.id.tv_kolactAct_unstart)
    private View view_bottom_unstart;
    @ViewInject(R.id.rel_kolactAct_start)
    private View view_bottom_start;
    @ViewInject(R.id.tv_kolactAct_end)
    private View view_bottom_end;
    @ViewInject(R.id.img_kolactAct_timeunstart)
    private ImageView img_time;
    @ViewInject(R.id.img_kolactAct_unstartbg)
    private ImageView img_bgtop;
    @ViewInject(R.id.progress_kolactAct)
    private ProgressBar progressBar_act;

    private int act_id;//活动id
    private KolActPresenter kolActPresenter = new KolActPresenter(this);

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            act_id = intentParmas.getActivity_id();
    }



    @Override
    protected void onStart() {
        super.onStart();
        TimeManager.getManager().syncServerTime();
        AppStatistics.onPageStart(this,"HowEnterLive_"+act_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppStatistics.onEvent(this,"how_enter_live","act_id",act_id+"");

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppStatistics.onPageEnd(this,"HowEnterLive_"+act_id);
    }

    @Override
    protected void setListener() {
        img_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCalendar();
            }
        });

        checkbox_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collect();

            }
        });
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kolActPresenter.share();
            }
        });
        view_bottom_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kolActPresenter.getActInfo() == null) {
                    showFailedError("还未获取到活动信息");
                    return;
                }
                JumpPageManager.getManager().goLiveActivity(getThis(), kolActPresenter.getActInfo());
            }
        });
        view_bottom_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kolActPresenter.getActInfo() == null) {
                    showFailedError("还未获取到活动信息");
                    return;
                }
                JumpPageManager.getManager().goLiveActivity(getThis(), kolActPresenter.getActInfo());
            }
        });
//
//        mSubscription = AppRxBus.getInstance().toObservable(AppTimeEvent.class).subscribe(new RxAction1<AppTimeEvent>() {
//            @Override
//            public void callback(AppTimeEvent appTimeEvent) {
//                kolActPresenter.checkKolActivity(appTimeEvent.getServer_time());
//
//            }
//        });
        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<AppTimeEvent>() {
            @Override
            protected void back(AppTimeEvent appTimeEvent) {
                kolActPresenter.checkKolActivity(appTimeEvent.getServer_time());

            }
        });
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_kol_activity;
    }

    @Override
    protected void initViews() {
        hideHeader();

    }

    @Override
    public void setStatusBar() {
        setTranslucenBar();
    }

    @Override
    protected void loadData() {
        kolActPresenter.loadData();
    }

    @Override
    public void setHashTag(String hashTag) {
        tv_actname.setText(hashTag);
    }

    @Override
    public void setActivityDesc(String desc) {
        tv_activitydesc.setText(desc);
    }

    @Override
    public void setDay(String day) {
        tv_day.setText(day);
    }

    @Override
    public void setMonth(String month) {
        tv_month.setText(month);
    }

    @Override
    public void setWeek(String week) {
        tv_weekandtime.setText(week);
    }

    @Override
    public void setLeftTime(String detailTime) {
        tv_detailtime.setText(detailTime);
    }

    @Override
    public void setKollistVp() {

    }

    @Override
    public void setCollectShow(boolean is) {
        checkbox_collect.setChecked(is);
    }

    @Override
    public View getVp() {
        return autoViewPager_kol;
    }

    @Override
    public void share() {
    }

    @Override
    public void collect() {
        kolActPresenter.collectActivity(getActivityId(), checkbox_collect.isChecked() ? "1" : "0");
    }

    @Override
    public void addToCalendar() {
        kolActPresenter.addToCalendar();
    }

    @Override
    public int getActivityId() {
        return act_id;
    }

    @Override
    public View getBottomUnstart() {
        return view_bottom_unstart;
    }

    @Override
    public View getBottomStart() {
        return view_bottom_start;
    }

    @Override
    public View getBottomEnd() {
        return view_bottom_end;
    }

    @Override
    public View getTopBg() {
        return img_bgtop;
    }

    @Override
    public View getTopTag() {
        return img_time;
    }

    @Override
    public View getAddCalendar() {
        return img_calendar;
    }

    @Override
    public CheckBox getCheckBox() {
        return checkbox_collect;
    }

    @Override
    public void setActProgress(int progress) {
        progressBar_act.setProgress(progress);
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        kolActPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (autoViewPager_kol != null)
            autoViewPager_kol.clearSelf();
        if (kolActPresenter != null)
            kolActPresenter.release_res();
    }
}
