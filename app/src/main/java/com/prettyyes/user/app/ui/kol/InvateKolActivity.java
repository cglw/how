package com.prettyyes.user.app.ui.kol;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SellerSearchListRequest;
import com.prettyyes.user.app.adapter.invate.InvateSellerModel;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/7/17.
 */

public class InvateKolActivity extends SingleListActivity<List<SellerInfoEntity>> {
    private String task_id;

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            task_id = intentParmas.getTask_id();

    }

    @Override
    public void needSearch(boolean needsearch) {
        super.needSearch(true);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_invatekol);
        swipeRv.getRecycleView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST).setHaveFootView());
        searchView.setQueryHint("搜索好手");
    }

    @Override
    protected void requestData(int page) {
        new SellerSearchListRequest().setPage(page).setTask_id(task_id).setNickname(inputText).post(this);
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(this);
    }

    @Override
    public void apiRequestSuccess(List<SellerInfoEntity> sellerInfoEntities,String method) {
        super.apiRequestSuccess(sellerInfoEntities,method);
    }

    @Override
    public List getListData(List<SellerInfoEntity> sellerInfoEntities) {
        List<InvateSellerModel> datas = new ArrayList<>();
        for (int i = 0; i < sellerInfoEntities.size(); i++) {
            InvateSellerModel in =new InvateSellerModel();
            in.setSeller(sellerInfoEntities.get(i));
            in.setTask_id(task_id);
            datas.add(in);
        }
        return datas;
    }
}
