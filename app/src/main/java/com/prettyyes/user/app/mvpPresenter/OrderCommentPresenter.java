package com.prettyyes.user.app.mvpPresenter;

import com.alibaba.fastjson.JSONArray;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OrderApiImpl;
import com.prettyyes.user.app.mvpView.OrderCommentView;
import com.prettyyes.user.core.event.OrderCommentCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.model.order.OrderCommentList;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/11/18
 * Description: Nothing
 */
public class OrderCommentPresenter {
    private OrderCommentView orderCommentView;
    private OrderApiImpl orderApi;

    public OrderCommentPresenter(OrderCommentView orderCommentView) {
        this.orderCommentView = orderCommentView;
        orderApi = new OrderApiImpl();
    }

    public void getListData() {
        orderApi.orderUintCommentList(orderCommentView.getThis().getUUId(), orderCommentView.getOrderNumber(), new NetReqCallback<OrderCommentList>() {
            @Override
            public void apiRequestFail(String message,String method) {

            }

            @Override
            public void apiRequestSuccess(OrderCommentList orderCommentList,String method) {
                orderCommentView.getAdapter().addAll((ArrayList<OrderCommentList.ListEntity>) orderCommentList.getList());

            }
        });
    }

    public void postCommentData() {
        String json = JSONArray.toJSONString(orderCommentView.getAdapter().getData());
        orderApi.ordercomment(orderCommentView.getThis().getUUId(), orderCommentView.getOrderNumber(), json, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message,String method) {
                orderCommentView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(Object o,String method) {
                orderCommentView.showFailedError("评论成功");
                AppRxBus.getInstance().post(new OrderCommentCompleteEvent());
                orderCommentView.getThis().back();
            }
        });
    }

}
