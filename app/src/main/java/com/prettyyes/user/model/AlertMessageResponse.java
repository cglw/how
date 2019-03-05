package com.prettyyes.user.model;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.account.AppInfo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.how.CouponGetActivity;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.data.DataCenter;

import static com.prettyyes.user.AppConfig.REGISTER_DIALOG_OPEN;

/**
 * Created by chengang on 2017/5/17.
 */

public class AlertMessageResponse {
    private int alert_message;
    private String newbie_task;

    public boolean isCompleteNewBie() {
        AppUtil.showToastShort(newbie_task);
        return newbie_task != null && newbie_task.length() > 0;
    }

    public String getNewbie_task() {
        return newbie_task;
    }

    public void setNewbie_task(String newbie_task) {
        this.newbie_task = newbie_task;
    }

    public int getAlert_message() {
        return alert_message;
    }

    public void setAlert_message(int alert_message) {
        this.alert_message = alert_message;
    }

    public static void isNeedShow(Object o, final DataCenter.CouponGetType type) {
        String s = GsonHelper.getGson().toJson(o);
        AlertMessageResponse alertMessageResponse = GsonHelper.getGson().fromJson(s, AlertMessageResponse.class);
        if (alertMessageResponse.isCompleteNewBie()) {
            AppRxBus.getInstance().post(new TaskCompleteEvent());
        }

        if (alertMessageResponse.getAlert_message() == 1)

        {
            new UserApiImpl().userFirstActivity(BaseApplication.UUID, new NetReqCallback<FirstModel>() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(FirstModel firstModel, String method) {
                    CouponGetActivity.goComponGetActivity(ZBundleCore.getInstance().getTopActivity(), type, firstModel);
                }
            });

        }

    }

    public static void isNeedShow(int alert_message, final DataCenter.CouponGetType type) {


        if (alert_message == 1)
            new UserApiImpl().userFirstActivity(BaseApplication.UUID, new NetReqCallback<FirstModel>() {
                @Override
                public void apiRequestFail(String message, String method) {

                }

                @Override
                public void apiRequestSuccess(FirstModel firstModel, String method) {
                    CouponGetActivity.goComponGetActivity(ZBundleCore.getInstance().getTopActivity(), type, firstModel);
                }
            });


    }


    public static boolean showUnregister() {


        AppInfo resolve = new AppInfo();
        resolve.setIshaveShowUnregister(true);
        resolve.setTime_unlogin(System.currentTimeMillis());
        resolve.setTag("tag");
        resolve.setIs_registered(1);
        SpMananger.getStorageProxy().save(Constant.SP_DATA_CENTER, resolve);
        if (REGISTER_DIALOG_OPEN)
            CouponGetActivity.goComponGetActivity(ZBundleCore.getInstance().getTopActivity(), DataCenter.CouponGetType.UNREGISTER, null);

        return true;

    }
}
