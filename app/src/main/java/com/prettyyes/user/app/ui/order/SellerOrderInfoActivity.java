package com.prettyyes.user.app.ui.order;

import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.AgreeCancelVerifyCodeRquest;
import com.prettyyes.user.api.netXutils.requests.SellerOrderInfoRequest;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.adapter.order.SellerInnerOrderAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.app.SettingItemView;
import com.prettyyes.user.app.view.lvandgrid.FullyGridLayoutManager;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.SendVerifyCodeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.SellerOrderInfo;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.ArrayList;

public class SellerOrderInfoActivity extends BaseActivity {

    private LinearLayout lin_person_info;
    private android.support.v7.widget.RecyclerView rv_goods_info;
    private LinearLayout lin_order_info;
    private LinearLayout lin_do_info;
    private SellerInnerOrderAdapter orderInfoInnerAdapter;

    private String[] titles_person_info = new String[]{"姓名", "电话", "地址"};
    private String[] titles_order_info = new String[]{"订单编号", "支付方式", "订单备注", "物流信息", "实际支付", "优惠金额"};
    private String[] titles_do_info = new String[]{"下单时间", "付款时间", "发货时间"};
    private String ordernumber = "";
    private String target_chat_id = "";

    private void bindViews() {
        lin_person_info = (LinearLayout) findViewById(R.id.lin_person_info);
        rv_goods_info = (android.support.v7.widget.RecyclerView) findViewById(R.id.rv_goods_info);
        lin_order_info = (LinearLayout) findViewById(R.id.lin_order_info);
        lin_do_info = (LinearLayout) findViewById(R.id.lin_do_info);
//        btn_agree_cancel = (Button) findViewById(btn_agree_cancel);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_seller_order_info;
    }

    @Override
    protected void initViews() {
        setActivtytitle(getString(R.string.title_activity_sellerorderinfo));
        bindViews();
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 1);
        rv_goods_info.setLayoutManager(manager);
        orderInfoInnerAdapter = new SellerInnerOrderAdapter(this);
        rv_goods_info.setAdapter(orderInfoInnerAdapter);
        IntentParams intentParams = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParams != null) {
            ordernumber = intentParams.order_number;
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        setRightTvListener("聊天", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDataRepo.getAccountManager().chatWithSeller(target_chat_id, getThis());
            }
        });

//        btn_agree_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setVisibility(View.GONE);
//                ll_show_agree.setVisibility(View.VISIBLE);
//            }
//        });
//
//        final VeriCodeView childAt = (VeriCodeView) ll_show_agree.getChildAt(0);
//        mSubscription = AppRxBus.getInstance().toObservable(SendVerifyCodeEvent.class).subscribe(new RxAction1<SendVerifyCodeEvent>() {
//            @Override
//            public void callback(final SendVerifyCodeEvent sendVerifyCodeEvent) {
//                new AgreeCancelVerifyCodeRquest().post(new NetReqCallback<Object>() {
//                    @Override
//                    public void apiRequestFail(String message, String method) {
//                    }
//
//                    @Override
//                    public void apiRequestSuccess(Object o, String method) {
////                        sendVerifyCodeEvent.getView().setVisibility(View.GONE);
//                    }
//                });
//
//            }
//        });

        mSubscription=AppRxBus.getInstance().subscribeEvent(new RxCallback<SendVerifyCodeEvent>() {
            @Override
            protected void back(SendVerifyCodeEvent sendVerifyCodeEvent) {
                new AgreeCancelVerifyCodeRquest().post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
//                        sendVerifyCodeEvent.getView().setVisibility(View.GONE);
                    }
                });

            }
        });
//        ll_show_agree.getChildAt(1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AgreeCancelorderRequest().setOrderNumber(ordernumber).setVerify(childAt.getVerifyCode()).post(new NetReqCallback<Object>() {
//                    @Override
//                    public void apiRequestFail(String message, String method) {
//                        showSnack(message);
//                    }
//
//                    @Override
//                    public void apiRequestSuccess(Object o, String method) {
//                        showSnack("退款成功");
//                        ll_show_agree.setVisibility(View.GONE);
//                    }
//                });


//                AgreecancelorderApi basePar = new AgreecancelorderApi();
//                basePar.setOrderNumber(ordernumber);
//                basePar.setVerify(childAt.getVerifyCode());
//                new HttpManager(new HttpOnNextListener<String>() {
//                    @Override
//                    public void onNext(String resulte, String mothead) {
//                        ToastUtils.showToast("退款成功");
//                        ll_show_agree.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        ToastUtils.showToast(e.getDisplayMessage());
//                    }
//                }, getThis()).doHttpDeal(basePar);

//            }
//        });

    }

    @Override
    protected void loadData() {
        new SellerOrderInfoRequest().setOrder_number(ordernumber).post(new NetReqCallback<SellerOrderInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                loadFail();

            }

            @Override
            public void apiRequestSuccess(SellerOrderInfo sellerOrderInfo, String method) {
                loadSuccess();
                setData(sellerOrderInfo);
            }
        });
    }


    private void setData(SellerOrderInfo resulte) {
        orderInfoInnerAdapter.addAll(resulte.getUnit_list());
        ArrayList<String> personInfo = new ArrayList<String>();
        personInfo.add(resulte.getGet_uname());
        personInfo.add(resulte.getUser_mobile());
        personInfo.add(resulte.getUser_address());
        for (int i = 0; i < titles_person_info.length; i++) {
            SettingItemView item = new SettingItemView(this);
            item.hideArrow();
            item.showSplit();
            item.setLeftText(titles_person_info[i]);
            item.setRightText(personInfo.get(i));
            lin_person_info.addView(item);
        }

        ArrayList<String> orderInfo = new ArrayList<String>();
        orderInfo.add(resulte.getOrder_number());
        orderInfo.add(resulte.getPay_type().equals("1") ? "支付宝" : "微信");
        orderInfo.add(resulte.getUser_remark());
        if (!StringUtils.isEmpty(resulte.getShip_number()))
            orderInfo.add("物流公司:" + resulte.getShip_company() + "\n运单号:" + resulte.getShip_number());
        else
            orderInfo.add("");
        orderInfo.add(resulte.getAmount_price());
        orderInfo.add(resulte.getCoupon_price());
        for (int i = 0; i < titles_order_info.length; i++) {
            SettingItemView item = new SettingItemView(this);
            item.hideArrow();
            item.showSplit();
            item.setLeftText(titles_order_info[i]);
            item.setRightText(orderInfo.get(i));
            lin_order_info.addView(item);
        }


        ArrayList<String> doInfo = new ArrayList<String>();
        doInfo.add(resulte.getCreate_time());
        doInfo.add(resulte.getPay_time());
        doInfo.add(resulte.getShip_time());
        for (int i = 0; i < titles_do_info.length; i++) {
            SettingItemView item = new SettingItemView(this);
            item.setLeftText(titles_do_info[i]);
            item.hideArrow();
            item.showSplit();
            item.setRightText(doInfo.get(i));
            lin_do_info.addView(item);
        }

//        if (resulte.getOrder_status()==2 && resulte.getShip_status()==0) {
//            btn_agree_cancel.setEnabled(true);
//        } else
//            btn_agree_cancel.setVisibility(View.GONE);

        target_chat_id = resulte.getUser_id();


    }
}
