package com.prettyyes.user.app.fragments;

import android.view.View;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.adapter.detail.HowActivtyAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.ActivityListModel;
import com.prettyyes.user.model.HowActivityModel;
import com.prettyyes.user.model.other.NotifyCount;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/12/12.
 */

public class MessageListFragment extends SingleListFragment {


    private void getNotify() {
        new OtherApiImpl().getNotityCount(BaseApplication.UUID, new NetReqCallback<NotifyCount>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(NotifyCount notifyCount, String method) {
                if (notifyCount != null) {
                    HowActivityModel a = new HowActivityModel();
                    a.setType("通知");
                    a.setReceivetime(FormatUtils.StringToDate(notifyCount.getCreate_time()));
                    a.setLastMeseage(notifyCount.getContent());
                    a.setTitle("How");
                    a.setHeadimg(notifyCount.getHeadimg());
                    a.setUnread(DataCenter.NOTIFY_UNREAD_NUM);
                    if (a != null) {
                        adapter.add(a);
                    }
                }

            }
        });
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        swipeRv.getSwpie().setEnabled(false);
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private void getHow() {
        new OtherApiImpl().getActivityList(new NetReqCallback<ActivityListModel>() {
            @Override
            public void apiRequestFail(String message, String method) {
            }

            @Override
            public void apiRequestSuccess(ActivityListModel activityListModel, String method) {
                if (activityListModel != null) {
                    HowActivityModel a = new HowActivityModel();
                    a.setType("官方");
                    a.setReceivetime(FormatUtils.StringToDate(activityListModel.getList().get(0).getCreate_time()));
                    a.setLastMeseage(activityListModel.getList().get(0).getActivity_describe());
                    a.setTitle(activityListModel.getList().get(0).getActivity_title());
                    a.setHeadimg(activityListModel.getList().get(0).getMain_img());
                    a.setUnread(DataCenter.ACTIVITY_UNREAD_NUM);
                    if (a != null) {
                        ((HowActivtyAdapter) adapter).setActivityLists((ArrayList<ActivityListModel.ListEntity>) activityListModel.getList());
                        adapter.add(a);
                    }
                }

            }
        });
    }



    @Override
    public void requestPageData(int page) {
        loadSuccess();
        getHow();
        getNotify();
        swipeRv.loadingEnd();
    }



    @Override
    public AbsRecycleAdapter createAdapter() {
        return new HowActivtyAdapter(getActivity());
    }
}
