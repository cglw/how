package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/10/10.
 */

public class AgreeOrderRefundRequest extends BaseRequest<Object> {
    private String order_number;
    private String verify;

    public AgreeOrderRefundRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }

    public AgreeOrderRefundRequest setVerify(String verify) {
        this.verify = verify;
        return this;

    }



    @Override
    public String setExtraUrl() {
        return "/app/order/agreeOrderRefund";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        arrayMap.put("verify", verify);
        return super.setParams();
    }
}
