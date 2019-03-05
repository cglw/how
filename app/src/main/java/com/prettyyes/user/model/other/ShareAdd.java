package com.prettyyes.user.model.other;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.how.CouponGetActivity;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.FirstModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/11/21
 * Description: Nothing
 */
public class ShareAdd {

    /**
     * increment : 0
     */
    private int alert_message;

    private int increment;
    private String newbie_task;

    public String getNewbie_task() {
        return newbie_task;
    }

    public void setNewbie_task(String newbie_task) {
        this.newbie_task = newbie_task;
    }

    public boolean isCompleteNewBie()
    {
        AppUtil.showToastShort(newbie_task);
        return newbie_task != null && newbie_task.length() > 0;
    }




    public int getAlert_message() {
        return alert_message;
    }

    public void setAlert_message(int alert_message) {
        this.alert_message = alert_message;
    }

    public static void isNeedShow(ShareAdd shareAdd) {

        if(shareAdd.isCompleteNewBie())
            AppRxBus.getInstance().post(new TaskCompleteEvent());
        if (shareAdd.getAlert_message() == 1) {

            new UserApiImpl().userFirstActivity(BaseApplication.UUID, new NetReqCallback<FirstModel>() {
                @Override
                public void apiRequestFail(String message,String method) {

                }

                @Override
                public void apiRequestSuccess(FirstModel firstModel,String method) {
                    CouponGetActivity.goComponGetActivity(ZBundleCore.getInstance().getTopActivity(), DataCenter.CouponGetType.SHARE, firstModel);
                }
            });
        }

    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
