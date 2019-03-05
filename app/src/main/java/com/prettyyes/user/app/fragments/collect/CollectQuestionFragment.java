package com.prettyyes.user.app.fragments.collect;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.netXutils.requests.CollectTaskListRequest;
import com.prettyyes.user.api.netXutils.response.MonmentsmyListRes;
import com.prettyyes.user.app.fragments.SingleListFragment;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;

import java.util.List;


public class CollectQuestionFragment extends SingleListFragment<MonmentsmyListRes> {


    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(AppConfig.DRIVID_HEIGHT_NORMAL).setNeed_top_margin(true);
    }

    @Override
    public void requestPageData(int page) {
        new CollectTaskListRequest(page).post(this);
    }

    @Override
    public List getListData(MonmentsmyListRes monmentsmyListRes) {
        for (int i = 0; i <monmentsmyListRes.getData().size() ; i++) {
            monmentsmyListRes.getData().get(i).setPage_type("home");
        }
        return monmentsmyListRes.getData();
    }
}
