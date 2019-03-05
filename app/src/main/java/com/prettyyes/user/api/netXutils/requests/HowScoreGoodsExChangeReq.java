package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/31.
 */

public class HowScoreGoodsExChangeReq extends BaseRequest<Object> {
    @Override
    public String setExtraUrl() {
        return "/app/howScore/spuOrder";
    }
    private String rechange_id;
    private String address_id;

    public HowScoreGoodsExChangeReq setRechange_id(String rechange_id) {
        this.rechange_id = rechange_id;
        return this;
    }

    public HowScoreGoodsExChangeReq setAddress_id(String address_id) {
        this.address_id = address_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("id",rechange_id);
        arrayMap.put("address_id",address_id);
        return super.setParams();
    }
}
