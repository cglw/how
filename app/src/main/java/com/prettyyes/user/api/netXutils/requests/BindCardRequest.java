package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/24.
 */

public class BindCardRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/bindcard";
    }
    private String bank_name;
    private String bank_card;
    private String bank_uname;
    private String bank_telephone;
    private String bank_branch;

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public void setBank_uname(String bank_uname) {
        this.bank_uname = bank_uname;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setBank_telephone(String bank_telephone) {
        this.bank_telephone = bank_telephone;
    }

    public BindCardRequest setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("bank_name", bank_name);
        arrayMap.put("bank_card", bank_card);
        arrayMap.put("bank_uname", bank_uname);
        arrayMap.put("bank_telephone", bank_telephone);
        arrayMap.put("bank_branch", bank_branch);
        return super.setParams();
    }
}
