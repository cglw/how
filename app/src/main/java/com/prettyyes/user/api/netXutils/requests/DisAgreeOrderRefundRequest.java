package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/10/10.
 */

public class DisAgreeOrderRefundRequest extends BaseRequest<Object> {
    private String order_number;
    private String disagree_reason;

    public DisAgreeOrderRefundRequest setOrder_number(String order_number) {
        this.order_number = order_number;
        return this;
    }


    public DisAgreeOrderRefundRequest setDisagree_reason(String disagree_reason) {
        this.disagree_reason = disagree_reason;
        return this;

    }



    @Override
    public String setExtraUrl() {
        return "/app/order/disagreeOrderRefund";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("order_number", order_number);
        arrayMap.put("disagree_reason", disagree_reason);
        return super.setParams();
    }
}
