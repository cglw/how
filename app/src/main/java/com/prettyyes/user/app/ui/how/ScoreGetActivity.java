package com.prettyyes.user.app.ui.how;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.ScoreGetListReq;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.recycle.RecyclerViewUtils;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.ScoreGetEntity;

import java.util.List;

/**
 * Created by chengang on 2017/12/27.
 */

public class ScoreGetActivity extends SingleListActivity<List<ScoreGetEntity>> {

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("赚How值，换现金！");
        swipeRv.getSwpie().setEnabled(false);
        swipeRv.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundWhit));
        swipeRv.setFootviewShow(false);
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_score_get_head, null);
        RecyclerViewUtils.setHeaderView(swipeRv.getRecycleView(), inflate);
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.layout_score_rule, null);
        RecyclerViewUtils.setFooterView(swipeRv.getRecycleView(), inflate1);

        mSubscription= AppRxBus.getInstance().subscribeEvent(new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent obj) {
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public List getListData(List<ScoreGetEntity> scoreGetEntities) {
        return scoreGetEntities;
    }


    @Override
    protected void requestData(int page) {
        new ScoreGetListReq().post(this);
    }

    @Override
    public void apiRequestSuccess(List<ScoreGetEntity> scoreGetEntities, String method) {
        super.apiRequestSuccess(scoreGetEntities, method);
        swipeRv.loadingEnd();
    }
}
