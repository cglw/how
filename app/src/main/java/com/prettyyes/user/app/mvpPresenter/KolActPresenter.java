package com.prettyyes.user.app.mvpPresenter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.app.adapter.KolListVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpView.KolActivityView;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.pagetransformer.ScaleVpTransformer;
import com.prettyyes.user.core.CalendarHandler;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.common.Info;
import com.prettyyes.user.model.system.TaskAct;
import com.prettyyes.user.model.v8.SellerInfoEntity;
import com.prettyyes.user.model.web.CalendarModel;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/2/10.
 */

public class KolActPresenter {
    private KolActivityView activityView;
    private TaskImpl netReq;
    private BaseActivity activity;
    private ActInfo activityInfo;
    private ArrayList<SellerInfoEntity> sellerInfoEntities;

    private static final String TAG = "KolActPresenter";

    public KolActPresenter(KolActivityView kolActivityView) {
        this.activityView = kolActivityView;
        netReq = new TaskImpl();
        activity = activityView.getThis();
    }

    private void getActivityInfo(final int act_id) {
        netReq.taskActivityInfo(activity.getUUId(), act_id, new NetReqCallback<TaskAct>() {
            @Override
            public void apiRequestFail(String message, String method) {
                activityView.showFailedError(message);
                activityView.getThis().loadFail();
            }

            @Override
            public void apiRequestSuccess(TaskAct taskAct, String method) {
                activityView.getThis().loadSuccess();

                activityInfo = taskAct.getInfo();
                checkKolActivity(TimeManager.getManager().getServer_time());
            }
        });
    }

    public void checkKolActivity(long server_time) {
        if (activityInfo != null) {
            long start = Long.parseLong(activityInfo.getStart_time());
            long end = Long.parseLong(activityInfo.getEnd_time());
            long create = Long.parseLong(activityInfo.getCreate_time());
            setCommonInfo(start, server_time, create);
            setContentByTime(start, end, server_time);
        }

    }


    public ArrayList<SellerInfoEntity> getSellers() {
        return sellerInfoEntities;
    }

    public void collectActivity(final int act_id, String isfollow) {
        if (activity.getUUId() == null) {
            RegisterActivity.getRegister(activityView.getThis());
            activityView.getCheckBox().setChecked(false);
            return;
        }
        if (activityInfo == null) {
            loadActivityFail();
            return;
        }

        netReq.followAct(activity.getUUId(), act_id, isfollow, new NetReqCallback<Info>() {
            @Override
            public void apiRequestFail(String message, String method) {
                activityView.showFailedError(message);
                activityView.getCheckBox().setChecked(!activityView.getCheckBox().isChecked());

            }

            @Override
            public void apiRequestSuccess(Info info, String method) {

                activityView.showFailedError("收藏" + (info.getInfo().equals("1") ? "成功" : "取消"));
                activityInfo.setIs_follow(info.getInfo());
            }

        });
    }


    public void loadData() {
        getActivityInfo(activityView.getActivityId());
    }

    KolListVpAdapter kolListVpAdapter;

    private void setVp(ArrayList<SellerInfoEntity> data, String act_name, boolean isstart) {
        sellerInfoEntities = data;
        if (kolListVpAdapter == null) {
            kolListVpAdapter = new KolListVpAdapter(activityView.getThis());
            kolListVpAdapter.setActInfo(activityView.getActivityId(), act_name, isstart);
            ((AutoViewPager) activityView.getVp()).setClip();
            ((AutoViewPager) activityView.getVp()).setIsneedIndict(false);
            ((AutoViewPager) activityView.getVp()).getVp().setPageTransformer(true, new ScaleVpTransformer(0.95f, ((AutoViewPager) activityView.getVp()).getVp()));
            ((AutoViewPager) activityView.getVp()).setVpMargin(DensityUtil.dip2px(24), 0);
            kolListVpAdapter.addAll(data);
            ((AutoViewPager) activityView.getVp()).setAbsVpAdapter(kolListVpAdapter);
        } else
            kolListVpAdapter.setCanSendque(isstart, (AutoViewPager) activityView.getVp());
    }

    private void setCommonInfo(long start, long now, long create) {
        activityView.setCollectShow(activityInfo.getIs_follow().equals("1") ? true : false);
        activityView.setActivityDesc(activityInfo.getAct_content());
        activityView.setHashTag(activityInfo.getAct_name());
        if (now < start) {
            activityView.setActProgress((int) ((now - create) * 100.0 / (start - create)));
        } else
            activityView.setActProgress(100);


        String time = FormatUtils.getDate(start * 1000);

        String day = time.substring(8, 10);
        String month = time.substring(5, 7);
        String week = FormatUtils.getWeekOfDate(start * 1000);
        String hour = time.substring(11, 13);
        String min = time.substring(14, 16);

        activityView.setActivityDesc(activityInfo.getAct_content());
        activityView.setDay(day);
        activityView.setMonth(FormatUtils.monthToChinese(month));

        activityView.setWeek(week + "   " + FormatUtils.getTopOrBottom(hour) + " " + FormatUtils.getHour_12(Integer.parseInt(hour)) + ":" + min);

    }


    private void setContentByTime(long start, long end, long now) {

        boolean isstart = false;

        boolean ishaveaddcalendar = false;
        if (now < start) {
            ishaveaddcalendar = true;
            activityView.setLeftTime("剩余" + FormatUtils.getLeftTime((start - now) * 1000));
            activityView.getBottomUnstart().setVisibility(View.VISIBLE);
            activityView.getBottomEnd().setVisibility(View.GONE);
            activityView.getBottomStart().setVisibility(View.GONE);
        } else if (start <= now && now < end) {
            isstart = true;
            activityView.setLeftTime("已开始");
            activityView.getBottomStart().setVisibility(View.VISIBLE);
            activityView.getBottomEnd().setVisibility(View.GONE);
            activityView.getBottomUnstart().setVisibility(View.GONE);

        } else if (now >= end) {
            activityView.setLeftTime("已结束");
            activityView.getBottomEnd().setVisibility(View.VISIBLE);
            activityView.getBottomStart().setVisibility(View.GONE);
            activityView.getBottomUnstart().setVisibility(View.GONE);
        }
        activityView.getAddCalendar().setVisibility(ishaveaddcalendar ? View.VISIBLE : View.INVISIBLE);
        ((ImageView) activityView.getTopBg()).setImageResource(isstart ? R.mipmap.kol_session_ready_bg : R.mipmap.kol_session_unstart_bg);
        ((ImageView) activityView.getTopTag()).setImageResource(isstart ? R.mipmap.kol_session_time_ready : R.mipmap.kol_session_time_unstart);
        setVp((ArrayList<SellerInfoEntity>) activityInfo.getSeller_list(), activityInfo.getHash_tag(), isstart);


    }


    public void addToCalendar() {
        PermissionUtils.requestSinglePermission(activity, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                if (activityInfo != null)
                    CalendarHandler.addCalendar(activity, getCalendarInfo());
                else
                    activityView.showFailedError("当前不可添加到日历提醒");
            }
        }, PermissionUtils.PERMISSION_WRITE_CALENDAR);
    }

    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(activity, requestCode, permissions, grantResults, new PermissionUtils.PermissionGrant() {
            @Override
            public void onPermissionGranted(int requestCode) {
                if (activityInfo != null)
                    CalendarHandler.addCalendar(activity, getCalendarInfo());
                else
                    activityView.showFailedError("当前不可添加到日历提醒");

            }
        });
    }

    private void loadActivityFail() {
        activityView.showFailedError("还未获取到活动信息");

    }

    public CalendarModel getCalendarInfo() {
        if (activityInfo == null) {
            activityView.showFailedError("未获取到信息");
        }
        CalendarModel calendarModel = new CalendarModel();
        calendarModel.setTitle("【How专场活动】" + activityInfo.getAct_name());
        calendarModel.setDesc(activityInfo.getAct_content());
        calendarModel.setEnd_time(activityInfo.getEnd_time() + "000");
        calendarModel.setStart_time(activityInfo.getStart_time() + "000");
        calendarModel.setRemindtime(activityInfo.getRemind_time());
        return calendarModel;
    }

    public ActInfo getActInfo() {
        return activityInfo;
    }

    public void share() {

        if (activityInfo == null) {
            activityView.showFailedError("未获取到分享信息");
            return;
        }

        new ShareHandler(activity).setRes(activityInfo.getShare_model(), new ShareHandler.ShareCallback() {
            @Override
            public void share(boolean issuccess) {

            }
        }).shareAtWindow(R.id.activity_kol_activity);
    }

    public void release_res() {

    }

}
