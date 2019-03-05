package com.prettyyes.user.app.mvpPresenter;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.DesUtils;
import com.prettyyes.user.app.mvpView.ActivityListView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.ActivityListModel;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/5
 * Description: Nothing
 */
public class ActivityListPresenter {
    private ActivityListView activityListView;

    public ActivityListPresenter(ActivityListView activityListView) {
        this.activityListView = activityListView;
    }

    public void getListData() {
        new OtherApiImpl().getActivityList(new NetReqCallback<ActivityListModel>() {
            @Override
            public void apiRequestFail(String message,String method) {
                activityListView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(ActivityListModel activityListModel,String method) {
                activityListView.setLvData((ArrayList<ActivityListModel.ListEntity>) activityListModel.getList());
            }
        });
    }

    public void handlerUrl(String url) {
        String loadurl = url;
        if (url.contains("?")) {
            loadurl += "&uuid=";
        } else {
            loadurl += "?uuid=";
        }
        try {
            if (activityListView.getThis().getUUId() != null) {
                loadurl += DesUtils.encryptionByDes(activityListView.getThis().getUUId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JumpPageManager.getManager().goWebActivity(activityListView.getThis(),loadurl);

//        WebviewActivity.goWebActivity(activityListView.getThis(), loadurl);
    }

}
