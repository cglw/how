package com.prettyyes.user.app.fragments;

import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.api.netXutils.requests.SellerorderListRequest;
import com.prettyyes.user.api.netXutils.response.SellerOrderListRes;
import com.prettyyes.user.app.adapter.order.SellerOrderAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.event.RefreshRefundChangeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;

import java.util.List;

/**
 * Created by chengang on 2017/7/27.
 */

public class SellerOrderListFragment extends SingleListFragment<SellerOrderListRes> {

    private String status;

    public static SellerOrderListFragment newInstance(String status) {
        SellerOrderListFragment simple = new SellerOrderListFragment();
        Bundle args = new Bundle();
        args.putString("status", status);
        simple.setArguments(args);
        return simple;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

       mSubscription= AppRxBus.getInstance().subscribeEvent(new RxCallback<RefreshRefundChangeEvent>() {
            @Override
            protected void back(RefreshRefundChangeEvent obj) {
                swipeRv.loadingFistEnter();
            }
        });
    }

    @Override
    public DividerItemDecoration setDrividHeightPx(int height) {
        return super.setDrividHeightPx(8);
    }

    @Override
    public void initParms(Bundle parms) {
        super.initParms(parms);
        status = parms.getString("status");
    }

    @Override
    public void requestPageData(int page) {
        new SellerorderListRequest().setStatus(status).post(this);

    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new SellerOrderAdapter(getContext());
    }

    @Override
    public List getListData(SellerOrderListRes sellerOrderListRes) {
        return sellerOrderListRes.list;
    }
}
