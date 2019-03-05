package com.prettyyes.user.app.ui.person;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.TaskMatchListRequest;
import com.prettyyes.user.api.netXutils.response.HomeListRes;
import com.prettyyes.user.app.adapter.MyReplyListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;

import java.util.List;

import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_MATCH;

/**
 * Created by chengang on 2017/7/26.
 */

public class MyReplyQueActivity extends SingleListActivity<HomeListRes> {
    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_my_replyque);
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
       return super.setDrividHeightPx(AppConfig.DRIVID_HEIGHT_NORMAL);
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new MyReplyListAdapter(this);
    }

    @Override
    protected void requestData(int page) {
        new TaskMatchListRequest().setPage(page).setStatus("1").setMethod(PAGE_MATCH).post(this);
    }

    @Override
    public List getListData(HomeListRes homeListRes) {
        return homeListRes.getList();
    }
}
