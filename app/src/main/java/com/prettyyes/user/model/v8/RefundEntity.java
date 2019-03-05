package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/10/10.
 */

public class RefundEntity {
    private String create_time;
    private String refund_reason;
    private String disagree_reason;
    private String expected_time;

    public String getExpected_time() {
        return expected_time;
    }

    private String refund_money;

    public String getCreate_time() {
        return create_time;
    }

    public String getDisagree_reason() {
        return disagree_reason;
    }

    public String getRefund_reason() {
        return refund_reason;
    }

    public String getRefund_money() {
        return refund_money;
    }
}
