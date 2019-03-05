package com.prettyyes.user.app.mvpPresenter;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.prettyyes.user.app.account.AppInfo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpView.MainActivityView;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.core.AlertMessageHandler;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.UpdateHandler;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.AlertMessageResponse;

import static com.prettyyes.user.AppConfig.UNREGISTER_SHOW;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class MainActivityPresenter {
    private static final String TAG = "MainActivityPresenter";
    private MainActivityView mainActivityView;
    private BaseActivity activity;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        activity = mainActivityView.getThis();
    }

    BadgeView badgeView;
    BadgeView badgeView_more;


    /**
     * 获取未读数
     */
    public void getTabFourTips() {
        if (activity.getUUId() == null) {
            if (badgeView != null) {
                badgeView.setHideOnNull(true);
            }
            return;
        }
        DataCenter.getUnread(DataCenter.UnreadType.COMMENT);
        DataCenter.getUnread(DataCenter.UnreadType.TASK);

    }

    /**
     * 获取未读数
     */
    public void getTabFiveTips() {
        if (activity.getUUId() == null) {
            if (badgeView != null) {
                badgeView.setHideOnNull(true);
            }
            return;
        }
        DataCenter.getUnread(DataCenter.UnreadType.MORE);
    }

    /**
     * 设置未读
     *
     * @param number
     */
    private void setBadgeView(int number, BadgeView badgeView, View target) {
        if (badgeView == null) {
            badgeView = new BadgeView(activity);
            badgeView.setBadgeMargin(0, 5, 15, 0);
            badgeView.setTargetView(target);
        }
        badgeView.setBadgeCount(number);

    }


    public void setBadgeViewDynamic(int number) {
        setBadgeView(number, badgeView, mainActivityView.getTipsView());

    }

    public void setBadgeViewMore(int number) {
        setBadgeView(number, badgeView_more, mainActivityView.getTipsMoreView());

    }


    public void selectIndexDynamic() {
        RadioButton radioButton = (RadioButton) mainActivityView.getRadioGroup().getChildAt(3);
        radioButton.setChecked(true);

    }


    public boolean onkeyDown(int keyCode, KeyEvent event) {
        if (activity.alertView != null && activity.alertView.isShowing() && activity.alertView.getTag() == 1) {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            activity.moveTaskToBack(true);
            return true;
        }

        return activity.onKeyDown(keyCode, event);
    }

    public void checkVersion() {
        activity.alertView = new UpdateHandler(activity).checkVersion();
    }

    public void loadAlertSplash() {
        handler.sendEmptyMessageDelayed(1, 500);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                new AlertMessageHandler(activity).loadAlertMessage();
            } else if (msg.what == 3) {

                left_time--;
                if (left_time > 0) {
                    handler.sendEmptyMessageDelayed(3, 1000);
                } else {
                    //show
                    AlertMessageResponse.showUnregister();

                }

            }
        }
    };

    public void checkGroupIndex(int index) {
        RadioButton radioButton = (RadioButton) mainActivityView.getRadioGroup().getChildAt(index);
        radioButton.setChecked(true);


    }

    public void release_res() {
        if (handler != null) {
            handler.removeMessages(1);
            handler.removeMessages(3);
        }
    }


    public void startShowNeedRegister() {
        if (StringUtils.strIsEmpty(activity.getUUId())) {
            AppInfo resolve = SpMananger.getStorageProxy().resolve(Constant.SP_DATA_CENTER, AppInfo.class);

            if (resolve == null) {
                resolve = new AppInfo();
                resolve.setTime_unlogin(System.currentTimeMillis());
                resolve.setIs_registered(0);
                SpMananger.getStorageProxy().save(Constant.SP_DATA_CENTER, resolve);

            }


            if (resolve.getIs_registered() == 0) {
                long time = (System.currentTimeMillis() - resolve.getTime_unlogin()) / 1000;
                left_time = UNREGISTER_SHOW - time;
                if (left_time > 0) {
                    //倒计时
                    handler.sendEmptyMessageDelayed(3, 1000);

                } else {
                    //显示
                    AlertMessageResponse.showUnregister();

                }
            }
        }
    }

    private long left_time = 0;

}
