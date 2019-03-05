package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/10/10.
 */

public class CloseOrderRefundRequest extends BaseRequest<Object> {
    private String order_number;

    public CloseOrderRefundRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }



    @Override
    public String setExtraUrl() {
        return "/app/order/closeOrderRefund";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        return super.setParams();
    }
}
