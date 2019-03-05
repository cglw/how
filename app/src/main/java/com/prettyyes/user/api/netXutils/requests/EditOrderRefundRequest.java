package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/10/10.
 */

public class EditOrderRefundRequest extends BaseRequest<Object> {
    private String order_number;
    private String refund_reason;

    public EditOrderRefundRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }

    public EditOrderRefundRequest setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
        return this;
    }


    @Override
    public String setExtraUrl() {
        return "/app/order/editOrderRefundReason";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        arrayMap.put("refund_reason", refund_reason);
        return super.setParams();
    }
}
