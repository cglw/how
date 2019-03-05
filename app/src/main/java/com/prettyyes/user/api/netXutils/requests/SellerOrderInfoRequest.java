package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.model.v8.SellerOrderInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/26.
 */

public class SellerOrderInfoRequest extends PageRequest<SellerOrderInfo> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/sellerOrderInfo";
    }

    private String order_number;

    public SellerOrderInfoRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        return super.setParams();
    }
}
