package com.prettyyes.user.app.ui.spu;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SpuListRequest;
import com.prettyyes.user.api.netXutils.response.SpuListRes;
import com.prettyyes.user.app.adapter.SpuListAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.recycle.HeaderSpanSizeLookup;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.ListChangeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;

import java.util.List;

public class SpuListActivity extends SingleListActivity<SpuListRes> {

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_spu_list);
        searchView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSoftModel() {

    }

    @Override
    public void needSearch(boolean needsearch) {
        super.needSearch(true);
    }

    @Override
    protected void requestData(int page) {
        new SpuListRequest().setPage(page).setSpu_value(inputText).post(this);
    }


    @Override
    protected void setListener() {
        super.setListener();
        setRightTvListener(R.string.spu_add_goods, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuSelectActivity(getThis(), false);
            }
        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddTemplateOrSelectSuccessEvent>() {
            @Override
            protected void back(AddTemplateOrSelectSuccessEvent obj) {
                swipeRv.loadingFistEnter();
            }
        }, new RxCallback<ListChangeEvent>() {
            @Override
            protected void back(ListChangeEvent obj) {
                swipeRv.setFocusable(true);
                swipeRv.requestFocus();
                swipeRv.setFocusableInTouchMode(true);
            }
        });


    }

    @Override
    public void setAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        swipeRv.setAdapter(adapter, gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new HeaderSpanSizeLookup(swipeRv.getHeaderAndFooterRecyclerViewAdapter(), gridLayoutManager.getSpanCount()));

    }


    @Override
    public AbsRecycleAdapter createAdapter() {
        return new SpuListAdapter(this);
    }


    @Override
    public List getListData(SpuListRes spuListRes) {
        return spuListRes.getData();
    }
}
