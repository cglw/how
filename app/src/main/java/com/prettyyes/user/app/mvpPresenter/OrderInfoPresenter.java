package com.prettyyes.user.app.mvpPresenter;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpView.OrderInfoView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.order.OrderManager;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.model.order.OrderInfo;

import java.util.ArrayList;

import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/3
 * Description: Nothing
 */
public class OrderInfoPresenter {
    public OrderInfoView orderInfoView;
    public OrderManager orderManager;
    private OrderInfo thisorderInfo;

    public OrderInfoPresenter(OrderInfoView orderInfoView) {
        this.orderInfoView = orderInfoView;
        orderManager = new OrderManager(orderInfoView.getThis());
    }

    public void getOrderInfo(String ordernum) {
        new OrderApiImpl().OrderGetInfo(orderInfoView.getThis().getUUId(), ordernum, new NetReqCallback<OrderInfo>() {
            @Override
            public void apiRequestFail(String message, String method) {
                orderInfoView.showFailedError(message);
                orderInfoView.getThis().loadFail();
            }

            @Override
            public void apiRequestSuccess(OrderInfo orderInfo, String method) {
                thisorderInfo = orderInfo;
                refreshAllData(orderInfo);
                orderInfoView.getThis().loadSuccess();

            }
        });
    }

    private void refreshAllData(OrderInfo orderInfo) {
        setTvData(orderInfo.getInfo());
        handlerData(orderInfo);
        orderInfoView.setSellerInfo(orderInfo.getInfo());
        orderInfoView.setLvData((ArrayList<OrderInfo.InfoEntity.MyListInfo>) orderInfo.getInfo().getMyListInfos());
    }

    private void setTvData(OrderInfo.InfoEntity info) {
        orderInfoView.setStep(getPoint(info), getArrayTv(info));
        orderInfoView.setYourname(info.getGet_uname());
        orderInfoView.setAddress(info.getUser_address());
        orderInfoView.setPhone(info.getUser_mobile());
        orderInfoView.setSellerName(info.getSeller_name(), info.getSeller_id() + "");
        orderInfoView.setSellerRemark(info.getUser_remark());
        orderInfoView.setOrderPrice(info.getTotal_price());
        orderInfoView.setOrderTotalNum(info.getUnit_num() + "");
        orderInfoView.setSubPrice(info.getTotal_price());
        orderInfoView.setPayPrice(info.getAmount_price());
        orderInfoView.setDiscountPrice(info.getCoupon_price());
        if (info.getCoupon_code_id() != 0) {
            orderInfoView.getConponText().setVisibility(View.GONE);
            orderInfoView.getCouponLayout().setVisibility(View.VISIBLE);
        } else {
            orderInfoView.getConponText().setVisibility(View.VISIBLE);
            orderInfoView.getCouponLayout().setVisibility(View.GONE);
        }
        orderInfoView.setCouponData(info.getCoupon_price(), info.getCoupon_name(), info.getCoupon_txt(), info.getCreate_time());
        setBtnTv(info);
        orderInfoView.setTopTime(info.getCreate_time(), info.getShip_time() == null ? "暂无" : info.getShip_time());
    }

    public void setBtnTv(OrderInfo.InfoEntity info) {
        orderInfoView.getBtnOne().setVisibility(View.GONE);
        orderInfoView.getBtnTwo().setVisibility(View.GONE);

        if (info.getAgree_cancel().equals("1") || info.getAgree_cancel().equals("2")) {
            return;
        } else if (info.getOrder_status().equals("3") && info.getIs_comment().equals("0")) {
            orderInfoView.getBtnTwo().setVisibility(View.VISIBLE);
            orderInfoView.getBtnTwo().setText("评价");
        } else if (info.getOrder_status().equals("2") && info.getShip_status().equals("0") && info.getAgree_cancel().equals("0")) {
            orderInfoView.getBtnOne().setVisibility(View.VISIBLE);
            orderInfoView.getBtnOne().setText("申请退款");
            orderInfoView.getBtnTwo().setVisibility(View.VISIBLE);
            orderInfoView.getBtnTwo().setText("提醒发货");

        } else if (info.getOrder_status().equals("2") && info.getShip_status().equals("0") && info.getAgree_cancel().equals("3")) {

            orderInfoView.getBtnOne().setVisibility(View.VISIBLE);
            orderInfoView.getBtnOne().setText("查看退款");
            orderInfoView.getBtnTwo().setVisibility(View.GONE);

        } else if (info.getOrder_status().equals("2") && (info.getShip_status().equals("1") || info.getShip_status().equals("2"))) {
            orderInfoView.getBtnOne().setVisibility(View.VISIBLE);
            orderInfoView.getBtnOne().setText("查看物流");
            orderInfoView.getBtnTwo().setVisibility(View.VISIBLE);
            orderInfoView.getBtnTwo().setText("确认收货");
        }
        orderInfoView.getBtnOne().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOrder((Button) v);
            }
        });
        orderInfoView.getBtnTwo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOrder((Button) v);
            }
        });

    }

    private void doOrder(Button v) {
        if (v.getText().toString().equals("申请退款") || v.getText().toString().equals("查看退款")) {
            if (orderInfoView.getSellerId() == null || orderInfoView.getSellerId().equals("")) {
                return;
            }
            JumpPageManager.getManager().goRefundActivityActivity(orderInfoView.getThis(), orderInfoView.getOrdernumber());
//            orderManager.backOrderMoney(orderInfoView.getSellerId(), orderInfoView.getOrdernumber());
        } else if (v.getText().toString().equals("查看物流")) {
            orderManager.lookOrderShip(orderInfoView.getOrdernumber());
        } else if (v.getText().toString().equals("提醒发货")) {
            orderManager.sendNotify(orderInfoView.getThis().getUUId(), orderInfoView.getOrdernumber());
        } else if (v.getText().toString().equals("确认收货")) {
            orderManager.confirmReceiveOrder(orderInfoView.getOrdernumber(), orderInfoView.getThis().getUUId());
        } else if (v.getText().toString().equals("评价")) {
            orderManager.commentOrderComment(orderInfoView.getOrdernumber());
        }
    }

    private void setLvHeight(OrderInfo orderInfo) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) orderInfoView.getLv().getLayoutParams();
        int height = 0;
        for (int i = 0; i < orderInfo.getInfo().getMyListInfos().size(); i++) {
            if (orderInfo.getInfo().getMyListInfos().get(i).getType().equals("title")) {
                height += 30;
            } else {
                height += 94;
            }
        }
        height += (orderInfo.getInfo().getMyListInfos().size() - 1) * 0.5;
        layoutParams.height = DensityUtil.dip2px(height);
    }

    private void handlerData(OrderInfo orderInfo) {
        orderInfo.getInfo().setMyListInfos(new ArrayList<OrderInfo.InfoEntity.MyListInfo>());
        for (int i = 0; i < orderInfo.getInfo().getUnit_list().getSuit().size(); i++) {
            OrderInfo.InfoEntity.MyListInfo mylistinfo = new OrderInfo.InfoEntity.MyListInfo();
            mylistinfo.setSuit_name(orderInfo.getInfo().getUnit_list().getSuit().get(i).getSuit_name());
            mylistinfo.setType("title");
            orderInfo.getInfo().getMyListInfos().add(mylistinfo);
            for (int j = 0; j < orderInfo.getInfo().getUnit_list().getSuit().get(i).getSuit_unit().size(); j++) {

                OrderInfo.InfoEntity.UnitListEntity.SuitEntity.SuitUnitEntity suitUnit = orderInfo.getInfo().getUnit_list().getSuit().get(i).getSuit_unit().get(j);
                OrderInfo.InfoEntity.MyListInfo infoinner = new OrderInfo.InfoEntity.MyListInfo();
                infoinner.setPrice(suitUnit.getPrice());
                infoinner.setType(TYPE_SUIT);
                infoinner.setId(suitUnit.getSuit_id());
                infoinner.setImg(suitUnit.getUnit_img());
                infoinner.setName(suitUnit.getUnit_name());
                infoinner.setNums(suitUnit.getUnit_nums());
                orderInfo.getInfo().getMyListInfos().add(infoinner);
            }

        }
        if (orderInfo.getInfo().getUnit_list().getTaobao() != null && orderInfo.getInfo().getUnit_list().getTaobao().size() > 0) {
            OrderInfo.InfoEntity.MyListInfo mylistinfo = new OrderInfo.InfoEntity.MyListInfo();
            mylistinfo.setSuit_name("淘宝");
            mylistinfo.setType("title");
            orderInfo.getInfo().getMyListInfos().add(mylistinfo);
        }
        for (int k = 0; k < orderInfo.getInfo().getUnit_list().getTaobao().size(); k++) {

            OrderInfo.InfoEntity.UnitListEntity.TaobaoEntity taobao = orderInfo.getInfo().getUnit_list().getTaobao().get(k);
            OrderInfo.InfoEntity.MyListInfo infoinner = new OrderInfo.InfoEntity.MyListInfo();
            infoinner.setPrice(taobao.getPrice());
            infoinner.setId(taobao.getUnit_id());
            infoinner.setType(TYPE_TAOBAO);
            infoinner.setImg(taobao.getUnit_img());
            infoinner.setName(taobao.getUnit_name());
            infoinner.setNums(taobao.getUnit_nums());
            orderInfo.getInfo().getMyListInfos().add(infoinner);
        }
        setLvHeight(orderInfo);
    }

    public void setIntentFliter(IntentFilter inentFliter) {
        inentFliter.addAction(Constant.OrderPaySuccess);
        inentFliter.addAction(Constant.OrderCommentComplete);
        inentFliter.addAction(Constant.OrderConfirmReceive);
    }

    public void handIntentFliter(Intent intent) {

        if (intent.getAction().equals(Constant.OrderPaySuccess)) {
            getOrderInfo(orderInfoView.getOrdernumber());
        } else if (intent.getAction().equals(Constant.OrderCommentComplete)) {
            getOrderInfo(orderInfoView.getOrdernumber());
        } else if (intent.getAction().equals(Constant.OrderConfirmReceive)) {
            getOrderInfo(orderInfoView.getOrdernumber());
        }

    }


    public int getPoint(OrderInfo.InfoEntity infoEntity) {


        switch (infoEntity.getOrder_status()) {
            case "1":
                return 0;
            case "2":
                if (infoEntity.getAgree_cancel().equals("2")) {
                    return 2;
                }
                return 1;
            case "3":
                return 2;
        }

        return 2;
    }

    public ArrayList<String> getArrayTv(OrderInfo.InfoEntity infoEntity) {
        ArrayList<String> a = new ArrayList<>();

        switch (infoEntity.getOrder_status()) {

            case "0":
                a.add("支付完成");
                a.add("配送完成");
                a.add("交易完成");
                break;
            case "1":
                a.add("待支付");
                a.add("正在配送");
                a.add("交易完成");
                break;
            case "2":
                if (infoEntity.getAgree_cancel().equals("1")) {
                    a.add("支付完成");
                    a.add("退款中");
                    a.add("交易完成");
                } else if (infoEntity.getAgree_cancel().equals("2")) {
                    a.add("支付完成");
                    a.add("退款成功");
                    a.add("交易完成");
                } else if (infoEntity.getAgree_cancel().equals("3")) {
                    a.add("支付完成");
                    a.add("退款中");
                    a.add("交易完成");
                } else if (infoEntity.getShip_status().equals("0")) {
                    a.add("支付完成");
                    a.add("待发货");
                    a.add("交易完成");
                } else if (infoEntity.getShip_status().equals("1")) {
                    a.add("支付完成");
                    a.add("已发货");
                    a.add("交易完成");
                }
                break;
            case "3":
                a.add("支付完成");
                a.add("配送完成");
                a.add("交易完成");
                break;
            default:
                a.add("支付完成");
                a.add("配送完成");
                a.add("交易完成");
                break;
        }
        return a;

    }

}
