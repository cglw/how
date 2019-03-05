package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/22.
 */

public class AdBlockReq extends BaseRequest<Object> {
    @Override
    public String setExtraUrl() {
        return "/app/adv/adBlock";
    }
    private String ad_id;

    public AdBlockReq setAd_id(String ad_id) {
        this.ad_id = ad_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("ad_id",ad_id);
        return super.setParams();
    }
}
