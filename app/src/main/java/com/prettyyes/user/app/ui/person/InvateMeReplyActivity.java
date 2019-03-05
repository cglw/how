package com.prettyyes.user.app.ui.person;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.TaskMatchListRequest;
import com.prettyyes.user.api.netXutils.response.HomeListRes;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.MyAskEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_MATCH;

/**
 * Created by chengang on 2017/7/26.
 */

public class InvateMeReplyActivity extends SingleListActivity<HomeListRes> {
    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_invate_me);
        DataCenter.clearInvateMeUnread();
    }


    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(AppConfig.DRIVID_HEIGHT_NORMAL);
    }

    @Override
    public List getListData(HomeListRes o) {
        List<MyAskEntity> data = new ArrayList<>();
        for (QuestionEntity d : o.getList()) {
            MyAskEntity myAskEntity = new MyAskEntity();
            myAskEntity.setInvateMe();
            myAskEntity.setTask(d);
            data.add(myAskEntity);
        }
        return data;
    }

    @Override
    protected void requestData(int page) {
        new TaskMatchListRequest().setPage(page).setStatus("3").setMethod(PAGE_MATCH).post(this);

    }


}
