package com.prettyyes.user.app.ui.order;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.ApplyOrderRefundRequest;
import com.prettyyes.user.api.netXutils.requests.CloseOrderRefundRequest;
import com.prettyyes.user.api.netXutils.requests.EditOrderRefundRequest;
import com.prettyyes.user.api.netXutils.requests.OrderBuyInfoRequest;
import com.prettyyes.user.api.netXutils.requests.SellerOrderInfoRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.RefundAgreeDialog;
import com.prettyyes.user.app.ui.appview.RefundrefuseDialog;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.RefreshRefundChangeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.order.OrderInfo;
import com.prettyyes.user.model.v8.SellerOrderInfo;

import static com.prettyyes.user.core.containter.JumpPageManager.SELLER;

public class RefundActivity extends BaseActivity {

    private TextView tv_refund_title;
    private LinearLayout ll_auto_refund_time;
    private TextView tv_auto_refund_time;
    private TextView tv_refund_reason;
    private EditText edit_refund_reason;
    private TextView tv_refund_price;
    private TextView tv_refund_time;
    private TextView tv_refund_change_reason;
    private TextView tv_refund_cancel;
    private TextView tv_refund_confirm;
    private TextView tv_refund_seller_agree;
    private TextView tv_refund_refuse;
    private TextView tv_order_info;
    private String order_number;
    private boolean edit = false;
    private String type;


    private void bindViews() {

        tv_refund_title = (TextView) findViewById(R.id.tv_refund_title);
        ll_auto_refund_time = (LinearLayout) findViewById(R.id.ll_auto_refund_time);
        tv_auto_refund_time = (TextView) findViewById(R.id.tv_auto_refund_time);
        tv_refund_reason = (TextView) findViewById(R.id.tv_refund_reason);
        edit_refund_reason = (EditText) findViewById(R.id.edit_refund_reason);
        tv_refund_price = (TextView) findViewById(R.id.tv_refund_price);
        tv_refund_time = (TextView) findViewById(R.id.tv_refund_time);
        tv_refund_change_reason = (TextView) findViewById(R.id.tv_refund_change_reason);
        tv_refund_cancel = (TextView) findViewById(R.id.tv_refund_cancel);
        tv_refund_confirm = (TextView) findViewById(R.id.tv_refund_confirm);
        tv_refund_seller_agree = (TextView) findViewById(R.id.tv_refund_seller_agree);
        tv_refund_refuse = (TextView) findViewById(R.id.tv_refund_refuse);
        tv_order_info = (TextView) findViewById(R.id.tv_order_info);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        order_number = intentParmas.getOrder_number();
        type = intentParmas.getType();


    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_refund;
    }

    @Override
    protected void initViews() {
        bindViews();
        setActivtytitle("退款");
    }

    private void refund_edited() {
        tv_refund_title.setText("正在申请退款");
        edit_refund_reason.setVisibility(View.GONE);
        tv_refund_reason.setVisibility(View.VISIBLE);

        tv_refund_confirm.setVisibility(View.GONE);
        tv_refund_cancel.setVisibility(View.VISIBLE);
        tv_refund_change_reason.setVisibility(View.VISIBLE);
        ll_auto_refund_time.setVisibility(View.VISIBLE);
        tv_refund_seller_agree.setVisibility(View.GONE);
        tv_refund_refuse.setVisibility(View.GONE);
    }

    private void refund_unedited() {
        tv_refund_title.setText("申请退款，待好手处理");
        edit_refund_reason.setVisibility(View.VISIBLE);
        tv_refund_reason.setVisibility(View.GONE);

        tv_refund_confirm.setVisibility(View.VISIBLE);
        tv_refund_cancel.setVisibility(View.GONE);
        tv_refund_change_reason.setVisibility(View.GONE);
        ll_auto_refund_time.setVisibility(View.GONE);
        tv_refund_seller_agree.setVisibility(View.GONE);
        tv_refund_refuse.setVisibility(View.GONE);
    }


    private void seller_edit() {
        tv_refund_title.setText("买家申请退款，待好手处理");
        edit_refund_reason.setVisibility(View.GONE);
        tv_refund_confirm.setVisibility(View.GONE);
        tv_refund_cancel.setVisibility(View.GONE);
        tv_refund_change_reason.setVisibility(View.GONE);
        ll_auto_refund_time.setVisibility(View.VISIBLE);
        tv_refund_seller_agree.setVisibility(View.VISIBLE);
        tv_refund_refuse.setVisibility(View.VISIBLE);
    }


    @Override
    protected void setListener() {
        super.setListener();
        tv_refund_change_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_refund_reason.setVisibility(View.VISIBLE);
                edit = true;
                refund_unedited();
            }
        });

        tv_refund_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CloseOrderRefundRequest().setOrder_number(order_number).post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showSnack(message);

                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        loadData();
                        AppRxBus.getInstance().post(new RefreshRefundChangeEvent());


                    }
                });


            }
        });
        tv_refund_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getReason().length() <= 0) {
                    showSnack("理由不可以为空");
                    return;
                }
                if (edit) {
                    new EditOrderRefundRequest().setOrder_number(order_number).setRefund_reason(getReason()).post(new NetReqCallback<Object>() {
                        @Override
                        public void apiRequestFail(String message, String method) {
                            edit = false;
                            showSnack(message);

                        }

                        @Override
                        public void apiRequestSuccess(Object o, String method) {
                            loadData();
                            edit = false;
                            AppRxBus.getInstance().post(new RefreshRefundChangeEvent());


                        }
                    });

                } else {
                    new ApplyOrderRefundRequest().setOrder_number(order_number).setRefund_reason(getReason()).post(new NetReqCallback<Object>() {
                        @Override
                        public void apiRequestFail(String message, String method) {
                            showSnack(message);
                        }

                        @Override
                        public void apiRequestSuccess(Object o, String method) {
                            loadData();
                            AppRxBus.getInstance().post(new RefreshRefundChangeEvent());

                        }
                    });
                }


            }
        });
        tv_refund_seller_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RefundAgreeDialog(getThis(), order_number).show();


            }
        });
        tv_refund_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RefundrefuseDialog(getThis(), order_number).show();

            }
        });
        tv_order_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type != null && type.equals(SELLER)) {
                    JumpPageManager.getManager().goSellerOrderInfoActivity(getThis(), order_number);

                } else
                    JumpPageManager.getManager().goMyOrderInfoActivity(getThis(), order_number);
            }
        });

    }

    public String getReason() {
        return edit_refund_reason.getText().toString();
    }

    @Override
    protected void loadData() {

        if (type.equals(SELLER)) {
            new SellerOrderInfoRequest().setOrder_number(order_number).post(new NetReqCallback<SellerOrderInfo>() {
                @Override
                public void apiRequestFail(String message, String method) {
                    loadFail();
                }

                @Override
                public void apiRequestSuccess(SellerOrderInfo sellerOrderInfo, String method) {
                    loadSuccess();

                    SellerOrderInfo info = sellerOrderInfo;
                    if (type != null && type.equals(SELLER)) {
                        seller_edit();
                    } else {

                        if (info.getAgree_cancel()==3) {
                            refund_edited();
                        } else {
                            refund_unedited();
                        }
                    }
                    edit_refund_reason.setText(info.getRefund().getRefund_reason());
                    if (info.getRefund().getRefund_reason() != null)
                        edit_refund_reason.setSelection(edit_refund_reason.getText().toString().length());
                    tv_refund_reason.setText(info.getRefund().getRefund_reason());
                    tv_refund_price.setText(StringUtils.getPrice(info.getRefund().getRefund_money()));
                    if (StringUtils.strIsEmpty(info.getRefund().getCreate_time()))
                        tv_refund_time.setVisibility(View.GONE);
                    else {
                        tv_refund_time.setVisibility(View.VISIBLE);
                        tv_refund_time.setText(info.getRefund().getCreate_time());
                    }
                    if (StringUtils.strIsEmpty(info.getRefund().getExpected_time()))
                        tv_auto_refund_time.setVisibility(View.GONE);
                    else {
                        tv_auto_refund_time.setVisibility(View.VISIBLE);
                        tv_auto_refund_time.setText(info.getRefund().getExpected_time());
                    }
                }
            });

        } else
            new OrderBuyInfoRequest().setOrder_number(order_number).post(new NetReqCallback<OrderInfo>() {
                @Override
                public void apiRequestFail(String message, String method) {
                    loadFail();

                }

                @Override
                public void apiRequestSuccess(OrderInfo orderInfo, String method) {
                    loadSuccess();
                    OrderInfo.InfoEntity info = orderInfo.getInfo();

                    if (type != null && type.equals(SELLER)) {
                        seller_edit();
                    } else {

                        if (info.getAgree_cancel().equals("3")) {
                            refund_edited();
                        } else {
                            refund_unedited();
                        }
                    }
                    edit_refund_reason.setText(info.getRefund().getRefund_reason());
                    if (info.getRefund().getRefund_reason() != null)
                        edit_refund_reason.setSelection(edit_refund_reason.getText().toString().length());
                    tv_refund_reason.setText(info.getRefund().getRefund_reason());
                    tv_refund_price.setText(StringUtils.getPrice(info.getRefund().getRefund_money()));
                    if (StringUtils.strIsEmpty(info.getRefund().getCreate_time()))
                        tv_refund_time.setVisibility(View.GONE);
                    else {
                        tv_refund_time.setVisibility(View.VISIBLE);
                        tv_refund_time.setText(info.getRefund().getCreate_time());
                    }
                    if (StringUtils.strIsEmpty(info.getRefund().getExpected_time()))
                        tv_auto_refund_time.setVisibility(View.GONE);
                    else {
                        tv_auto_refund_time.setVisibility(View.VISIBLE);
                        tv_auto_refund_time.setText(info.getRefund().getExpected_time());
                    }


                }
            });
    }


}
