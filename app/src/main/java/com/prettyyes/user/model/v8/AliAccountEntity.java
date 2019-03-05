package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/24.
 */

public class AliAccountEntity extends BaseModel {
    private String alipay_username;
    private String alipay_account;
    private String alipay_telephone;

    public String getAlipay_username() {
        return alipay_username;
    }

    public void setAlipay_username(String alipay_username) {
        this.alipay_username = alipay_username;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public String getAlipay_telephone() {
        return alipay_telephone;
    }

    public void setAlipay_telephone(String alipay_telephone) {
        this.alipay_telephone = alipay_telephone;
    }
}
