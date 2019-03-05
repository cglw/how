package com.prettyyes.user.app.ui.how;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.TaskNewUserListReq;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.recycle.RecyclerViewUtils;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.v8.TaskNewUserItemEntity;

import java.util.List;

/**
 * Created by chengang on 2017/12/27.
 */

public class TaskNewUserActivity extends SingleListActivity<ListCommon<List<TaskNewUserItemEntity>>> {
    @Override
    protected void requestData(int page) {
        new TaskNewUserListReq().post(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        swipeRv.getSwpie().setEnabled(false);
        setActivtytitle("新手任务");
        swipeRv.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundWhit));
        swipeRv.setFootviewShow(false);
        RecyclerViewUtils.setHeaderView(swipeRv.getRecycleView(), LayoutInflater.from(this).inflate(R.layout.layout_task_newuser_head, null));
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent obj) {
                if (swipeRv != null)
                    swipeRv.loadingFistEnter();
            }
        }, new RxCallback<TaskCompleteEvent>() {
            @Override
            protected void back(TaskCompleteEvent obj) {
                for (int i = 0; i < adapter.getItems().size(); i++) {
                    TaskNewUserItemEntity itemData = (TaskNewUserItemEntity) adapter.getItemData(i);
                    if (!itemData.isComplete()) {
                        if (swipeRv != null)
                            swipeRv.loadingFistEnter();
                        break;
                    }
                }
            }
        });

    }

    @Override
    public List getListData(ListCommon<List<TaskNewUserItemEntity>> listInfoCommon) {
        return listInfoCommon.getList();
    }

    @Override
    public void apiRequestSuccess(ListCommon<List<TaskNewUserItemEntity>> listListCommon, String method) {
        super.apiRequestSuccess(listListCommon, method);
        swipeRv.loadingEnd();
    }
}
