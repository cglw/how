package com.prettyyes.user.app.ui.person;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.MyTaskRequest;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.v8.HomeTaskEntity;
import com.prettyyes.user.model.v8.MyAskEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/7/26.
 */

public class MyAskActivity extends SingleListActivity<ListCommon<List<HomeTaskEntity>>> {
    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_my_asklist);
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(16);
    }

    @Override
    protected void requestData(int page) {
        new MyTaskRequest().setPage(page).post(this);
    }


    @Override
    public List getListData(ListCommon<List<HomeTaskEntity>> res) {

        List<MyAskEntity> data = new ArrayList<>();
        for (QuestionEntity d : res.getList()) {

            MyAskEntity myAskEntity = new MyAskEntity();
            myAskEntity.setTypeMyAsk();
            myAskEntity.setTask(d);
            data.add(myAskEntity);
        }


        return data;
    }

}
