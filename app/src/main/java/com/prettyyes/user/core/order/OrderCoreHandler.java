package com.prettyyes.user.core.order;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.order.OrderCreate;
import com.prettyyes.user.model.order.OrderGoPay;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class OrderCoreHandler implements OrderCoreApi {

    private OrderApiImpl orderApi;
    private OrderCallback orderCallback;

    public OrderCoreHandler(OrderCallback orderCallback) {
        orderApi = new OrderApiImpl();
        this.orderCallback = orderCallback;

    }

    @Override
    public void createOrder(String uuid, String user_remark, int address_id, int coupon_id, int buy_now, int pay_type) {
        orderApi.orderCreate(uuid, user_remark, pay_type, coupon_id, buy_now, address_id, new NetReqCallback<OrderCreate>() {
            @Override
            public void apiRequestFail(String message,String method) {
                AppUtil.showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(OrderCreate orderCreate,String method) {
                if (orderCallback != null) {
                    orderCallback.alipayData(orderCreate.getInfo().getAlipay().getStr());
                    orderCallback.weixinData(orderCreate.getInfo().getWechat_pay());
                }
            }
        });
    }


    @Override
    public void payOrder(String uuid, String order_number, int pay_type) {
        orderApi.orderGopay(uuid, order_number, pay_type, new NetReqCallback<OrderGoPay>() {
            @Override
            public void apiRequestFail(String message,String method) {
                AppUtil.showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(OrderGoPay orderGoPay,String method) {


                if (orderCallback != null) {

                    orderCallback.alipayData(orderGoPay.getAlipay().getStr());
                    orderCallback.weixinData(orderGoPay.getWechat_pay());
                }

            }
        });
    }

    @Override
    public void rewardOrder(String uuid, int pay_type, String reward_type, int reward_type_id,int task_id,float price,String ...params) {
        orderApi.orderReward(uuid, pay_type, reward_type, reward_type_id,task_id,price, new NetReqCallback<OrderCreate>() {
            @Override
            public void apiRequestFail(String message,String method) {
                AppUtil.showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(OrderCreate orderCreate,String method) {
                if (orderCallback != null) {
                    orderCallback.alipayData(orderCreate.getInfo().getAlipay().getStr());
                    orderCallback.weixinData(orderCreate.getInfo().getWechat_pay());
                }
            }
        },params);
    }

}
