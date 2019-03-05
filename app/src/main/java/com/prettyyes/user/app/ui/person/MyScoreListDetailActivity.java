package com.prettyyes.user.app.ui.person;

import com.prettyyes.user.api.netXutils.requests.UserScoreListRequest;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.v8.ScoreEntity;

import java.util.List;

/**
 * Created by chengang on 2017/9/4.
 */

public class MyScoreListDetailActivity extends SingleListActivity<List<ScoreEntity>> {
    @Override
    public void setActivtytitle(String title) {
        super.setActivtytitle("How值详情");
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(AppUtil.dip2px(0.5));
    }

    @Override
    protected void requestData(int page) {
        new UserScoreListRequest().post(this);

    }

    @Override
    public List getListData(List<ScoreEntity> userScoreListRes) {
        return userScoreListRes;
    }

    @Override
    public void apiRequestSuccess(List<ScoreEntity> scoreEntities, String method) {
        super.apiRequestSuccess(scoreEntities, method);
        swipeRv.loadingEnd();
    }
}
