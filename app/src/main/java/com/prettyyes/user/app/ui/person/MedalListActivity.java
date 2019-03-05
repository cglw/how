package com.prettyyes.user.app.ui.person;

import android.support.v4.content.ContextCompat;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.MedalListRequest;
import com.prettyyes.user.api.netXutils.response.MedalListRes;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalListActivity extends SingleListActivity<MedalListRes> {


    @Override
    protected void initViews() {
        super.initViews();
        getRootView().setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundWhit));
        setActivtytitle(R.string.title_activity_medallist);
        swipeRv.getSwpie().setEnabled(false);
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(16);
    }

    @Override
    protected void requestData(int page) {
        new MedalListRequest().post(this);
    }

    @Override
    public void apiRequestSuccess(MedalListRes medalListRes, String method) {
        super.apiRequestSuccess(medalListRes, method);
        swipeRv.loadingEnd();
    }


    @Override
    public List getListData(MedalListRes medalListRes) {
        return medalListRes.getList();
    }
}
