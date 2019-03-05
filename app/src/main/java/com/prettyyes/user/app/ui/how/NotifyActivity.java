package com.prettyyes.user.app.ui.how;

import com.prettyyes.user.api.netXutils.requests.NotifyListRequest;
import com.prettyyes.user.app.adapter.NotifyAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.model.other.NotifyContentList;

import java.util.List;

/**
 * Created by chengang on 2017/4/19.
 * <p>
 * app  内部通知页面
 */

public class NotifyActivity extends SingleListActivity<NotifyContentList> {

    @Override
    protected void requestData(int page) {
        new NotifyListRequest().setPage(page).post(this);
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(1);
    }


    @Override
    public AbsRecycleAdapter createAdapter() {
        return new NotifyAdapter(this);
    }

    @Override
    public List getListData(NotifyContentList notifyContentList) {
        return notifyContentList.getData();
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("通知");

    }
}
