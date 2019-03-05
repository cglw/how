package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.response.SellerOrderListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/26.
 */

public class SellerorderListRequest extends PageRequest<SellerOrderListRes> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/sellerOrderList";
    }
    private String status;
    private String order_number;

    public SellerorderListRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("status",status);
        arrayMap.put("order_number",order_number);
        return super.setParams();
    }

    public SellerorderListRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }
}
