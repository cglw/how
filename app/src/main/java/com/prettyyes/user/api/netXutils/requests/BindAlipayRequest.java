package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/24.
 */

public class BindAlipayRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/bindalipay";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("alipay_account", alipay_account);
        arrayMap.put("alipay_username", alipay_username);
        arrayMap.put("alipay_telephone", alipay_telephone);
        return super.setParams();
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public void setAlipay_username(String alipay_username) {
        this.alipay_username = alipay_username;
    }

    public void setAlipay_telephone(String alipay_telephone) {
        this.alipay_telephone = alipay_telephone;
    }

    private String alipay_account;
    private String alipay_username;
    private String alipay_telephone;
}
