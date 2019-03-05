package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.order.OrderInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2017/10/9.
 */

public class OrderBuyInfoRequest extends BaseRequest<OrderInfo> {
    @Override
    public String setExtraUrl() {
        return "/app/order/getInfo";
    }

    private String order_number;

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        return super.setParams();
    }

    public OrderBuyInfoRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }
}
