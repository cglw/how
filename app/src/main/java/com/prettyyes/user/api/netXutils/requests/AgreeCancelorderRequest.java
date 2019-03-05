package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/27.
 * 
 */

public class AgreeCancelorderRequest extends BaseRequest<Object> {
    private String orderNumber;
    private String verify;


    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/agreecancelorder";
    }

    public AgreeCancelorderRequest setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public AgreeCancelorderRequest setVerify(String verify) {
        this.verify = verify;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("orderNumber", orderNumber);
        arrayMap.put("verify", verify);
        return super.setParams();
    }
}
