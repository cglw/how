package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalOrderRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/medal/medalOrder";
    }

    private String medal_id;
    private String address_id;

    public MedalOrderRequest setMedal_id(String medal_id) {
        this.medal_id = medal_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("medal_id", medal_id);
        arrayMap.put("address_id", address_id);
        return super.setParams();
    }

    public MedalOrderRequest setAddress_id(String address_id) {
        this.address_id = address_id;
        return this;
    }


}
