package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.adapter.order.OrderListAdapter;
import com.prettyyes.user.app.adapter.order.OrderListChildAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.app.view.lvandgrid.SwipeListView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.OrderCommentCompleteEvent;
import com.prettyyes.user.core.event.OrderPayCancelEvent;
import com.prettyyes.user.core.event.OrderPaySuccessEvent;
import com.prettyyes.user.core.event.RefreshRefundChangeEvent;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.order.OrderList;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.AppConfig.MIN_PAGE_SIZE;

public class OrderListFragment extends BaseFragment implements EmptyListener {
    @ViewInject(R.id.swpielist_orderlistFmt_showorder)
    private SwipeListView swipeListView;
    private OrderListAdapter orderListAdapter;
    private int status;
    private OrderManager orderManager = null;

    public OrderListFragment() {

    }

    public static OrderListFragment newInstance(int status) {
        OrderListFragment orderlistfragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putInt("status", status);
        orderlistfragment.setArguments(args);
        return orderlistfragment;
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_order_list;
    }

    @Override
    public View bindView() {
        return null;
    }


    @Override
    public void initParms(Bundle parms) {
        status = parms.getInt("status");
    }

    @Override
    public void initView(View view) {

        orderManager = new OrderManager(getActivity());
        orderListAdapter = new OrderListAdapter(getActivity(), new OrderListChildAdapter.OrderCallback() {
            @Override
            public void pay(String ordernumber) {
                ((BaseActivity) getActivity()).alertView = orderManager.OrderPay(getUUId(), ordernumber);
            }

            @Override
            public void cancelPay(String ordernumber) {

            }

            @Override
            public void notiySend(String ordernumber) {
                orderManager.sendNotify(getUUId(), ordernumber);
            }

            @Override
            public void backmoney(String ordernumber, String Seller_id) {
//                orderManager.backOrderMoney(Seller_id, ordernumber);
                JumpPageManager.getManager().goRefundActivityActivity(getContext(), ordernumber);
            }

            @Override
            public void commentOrder(String ordernumber) {
                orderManager.commentOrderComment(ordernumber);

            }

            @Override
            public void lookship(String ordernumber) {
                orderManager.lookOrderShip(ordernumber);
            }

            @Override
            public void confirmReceive(String ordernumber) {
                orderManager.confirmReceiveOrder(ordernumber, getUUId());
            }
        });
        swipeListView.setAdapter(orderListAdapter);
        swipeListView.setEmpty_listener(this);
        swipeListView.getListView().setDivider(new ColorDrawable(ContextCompat.getColor(getContext(),R.color.backgroundWhit)));
        swipeListView.getListView().setDividerHeight(AppUtil.dip2px(8));

    }

    @Override
    public void setListener() {
        swipeListView.setListener(new SwipeListView.LoadCallback() {
            @Override
            public void loadList(int page) {
                getListData(page);
            }
        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<OrderPaySuccessEvent>() {
            @Override
            protected void back(OrderPaySuccessEvent obj) {
                refreshOrderList();

            }
        }, new RxCallback<OrderPayCancelEvent>() {
            @Override
            protected void back(OrderPayCancelEvent obj) {
                refreshOrderList();

            }
        }, new RxCallback<OrderCommentCompleteEvent>() {
            @Override
            protected void back(OrderCommentCompleteEvent obj) {
                refreshOrderList();

            }
        }, new RxCallback<RefreshRefundChangeEvent>() {
            @Override
            protected void back(RefreshRefundChangeEvent obj) {
                refreshOrderList();

            }
        });

    }

    private void getListData(final int page) {
        new OrderApiImpl().OrderGetList(getUUId(), status, page, new NetReqCallback<OrderList>() {
            @Override
            public void apiRequestFail(String message, String method) {
                swipeListView.loadingfail();
                if (page == 1)
                    loadFail();
            }

            @Override
            public void apiRequestSuccess(OrderList orderList, String method) {
                loadSuccess();
                if (orderList.getList().size() < MIN_PAGE_SIZE) {
                    swipeListView.loadingEnd();
                } else {
                    swipeListView.loadingSuccessHavedata();
                }
                orderListAdapter.addAll((ArrayList<OrderList.ListEntity>) orderList.getList());
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }


    @Override
    public void lazyInitBusiness(Context mContext) {
        swipeListView.loadPageData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeListView.closeAnim();
    }


    private void refreshOrderList() {
        if (status == 2 || status == 4) {
            swipeListView.loadingFistEnter();
        }
    }

    private void handlerComplete(String ordernum) {
        for (int i = 0; i < orderListAdapter.getCount(); i++) {
            for (int j = 0; j < orderListAdapter.get(i).getOrder_child().size(); j++) {
                if (orderListAdapter.get(i).getOrder_child().get(j).getOrder_number().equals(ordernum)) {
                    orderListAdapter.get(i).getOrder_child().get(j).setOrder_status("3");
                    orderListAdapter.notifyDataSetChanged();
                    return;
                }

            }

        }
    }

    private void hnadlerConfirm(String ordernumber) {
        for (int i = 0; i < orderListAdapter.getCount(); i++) {
            for (int j = 0; j < orderListAdapter.get(i).getOrder_child().size(); j++) {
                if (orderListAdapter.get(i).getOrder_child().get(j).getOrder_number().equals(ordernumber)) {
                    orderListAdapter.get(i).getOrder_child().get(j).setShip_status("2");
                    orderListAdapter.notifyDataSetChanged();
                    return;
                }

            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        swipeListView.clearSelf();
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {
        TextView tv1 = (TextView) ll.getChildAt(0);
        TextView tv2 = (TextView) ll.getChildAt(1);
        TextView tv3 = (TextView) ll.getChildAt(2);
        button.setVisibility(View.GONE);
        ll.setVisibility(View.VISIBLE);
        switch (status) {
            //已付款
            case 4:
                tv1.setText("达洛维夫人说她自己去买花。");
                tv2.setText("——伍尔夫");
                tv3.setText("您还没有已付款的订单，当下中意的好物千万别犹豫到下一秒。");
                break;
            //待支付
            case 2:
                tv1.setText("那吻一下如何？像电影一样。");
                tv2.setText("——《这个杀手不太冷》");
                tv3.setText("您的未付款页面为空，这很棒，爱不该迟疑");
                break;
        }
    }
}
