package com.prettyyes.user.app.mvpPresenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.BannerRequest;
import com.prettyyes.user.api.netXutils.requests.TaskActPreRequest;
import com.prettyyes.user.app.mvpView.OtherQueView;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.event.AppTimeEvent;
import com.prettyyes.user.core.utils.AnimotionUtils;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.common.AppBanner;
import com.prettyyes.user.model.system.TaskActPre;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/12/14
 * Description: Nothing
 */
public class OtherQuePresenter {
    private static final String TAG = "OtherQuePresenter";
    private OtherQueView otherQueView;
    private ActInfo taskinfo;

    public OtherQuePresenter(OtherQueView otherQueView) {
        this.otherQueView = otherQueView;

    }

    public void getBanner() {

        otherQueView.getBannerVp().setIscanClickOther(false);
        new BannerRequest().setBanner_type("home_page").post(new NetReqCallback<AppBanner>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(AppBanner appBanner, String method) {
                otherQueView.getVpAdapter().clear();

                if (appBanner.getList().size() <= 0) {
                    hideBanner();
                    return;
                } else {

                    otherQueView.getVpAdapter().clear();
                    otherQueView.getVpAdapter().addAll((ArrayList<AppBanner.ListEntity>) appBanner.getList());
                    otherQueView.getBannerVp().setAbsVpAdapter(otherQueView.getVpAdapter());
                    showBanner();
                }
            }
        });

    }

    public void getActivity() {
        new TaskActPreRequest().post(new NetReqCallback<TaskActPre>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(TaskActPre taskAct, String method) {
                if (taskAct.getInfo() == null) {
                    hideActivity();
                    return;
                }
                taskinfo = taskAct.getInfo();
                if (taskinfo.getAct_id() == 0)
                    return;
                start = Long.parseLong(taskinfo.getStart_time());
                end = Long.parseLong(taskinfo.getEnd_time());

                TimeManager.getManager().syncKolActivityTime(Long.parseLong(taskinfo.getServer_time()), start, end, 0);
                TimeManager.getManager().syncServerTime();
                otherQueView.getHomeEnter().setActInfo(taskAct.getInfo());
//                otherQueView.getHomeEnter().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        goKolActActivity();
//                    }
//                });

            }
        });

    }


    private void hideBanner() {

        if (otherQueView.getBannerView().getVisibility() == View.VISIBLE) {
            otherQueView.getBannerView().setVisibility(View.GONE);
//            AnimotionUtils.animateClose(otherQueView.getBannerView(), new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    otherQueView.notifyRv();
//
//                }
//            });
        }


    }


    private void showBanner() {
        if (otherQueView.getBannerView().getVisibility() == View.GONE||otherQueView.getBannerView().getVisibility() == View.INVISIBLE) {
            otherQueView.getBannerView().setVisibility(View.VISIBLE);
            AnimotionUtils.animateAlphaIn(otherQueView.getBannerView());
            otherQueView.getBannerVp().initIndicator();

//            AnimotionUtils.animateOpen(otherQueView.getBannerView(), (int) (BaseApplication.ScreenWidth * 0.3), 1000, new AnimotionUtils.AnimotionCallback() {
//                        @Override
//                        public void end() {
//                            otherQueView.getBannerView().setVisibility(View.VISIBLE);
//
//                        }
//                    }
//            );
        } else {
            otherQueView.getBannerVp().initIndicator();

        }
    }


    private void hideActivity() {
        if (otherQueView.getHomeEnter().getVisibility() == View.VISIBLE)
            AnimotionUtils.animateClose(otherQueView.getHomeEnter(), new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                }
            });

    }


    private void hideActivityUnstart() {
        hideActivity();

    }

    private void showActivity() {
        if (otherQueView.getHomeEnter().getVisibility() == View.GONE) {
            AnimotionUtils.animateOpen(otherQueView.getHomeEnter(), DensityUtil.dip2px(66), new AnimotionUtils.AnimotionCallback() {
                @Override
                public void end() {
                }
            });
        }
    }

    private void showActivityUnstart() {
        showActivity();
        if (taskinfo != null) {
            otherQueView.getHomeEnter().setTime(FormatUtils.getDate(start * 1000));
            otherQueView.getHomeEnter().setTitle(taskinfo.getAct_name());
            otherQueView.getHomeEnter().setImg_bg(false);
            otherQueView.getHomeEnter().setTv_click("报名参加");

        }


    }

    private void showActibvityStart() {
        showActivity();
        if (taskinfo != null) {
            long time_toend_start = end - time_now;
            otherQueView.getHomeEnter().setTime("剩余：" + FormatUtils.getLeftTimeNum(time_toend_start));
            otherQueView.getHomeEnter().setTitle(taskinfo.getAct_name());
            otherQueView.getHomeEnter().setImg_bg(true);
            otherQueView.getHomeEnter().setActInfo(taskinfo);
            otherQueView.getHomeEnter().setTv_click("立即参与");


        }
    }


    private long time_now = 0;
    private long start = 0;
    private long end = 0;


    public void checkKolActivity(AppTimeEvent appTimeEvent) {
        time_now = appTimeEvent.getServer_time();
        if (time_now > end) {
            hideActivity();
        } else if (time_now >= start && time_now <= end) {
            showActibvityStart();
        } else if (start - time_now <= 24 * 60 * 60) {//小于等于一天
            showActivityUnstart();
        } else if (start - time_now > 24 * 60 * 60) {
            hideActivityUnstart();
        }
    }

}
