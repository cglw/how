package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/27.
 */

public class RemindshipRequest extends BaseRequest<Object> {


    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/remindship";
    }

    public String order_number;

    public RemindshipRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        return super.setParams();
    }
}
