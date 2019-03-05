package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.MedalListRes;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.user.UserInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalListRequest extends BaseRequest<MedalListRes> {

    @Override
    public String setExtraUrl() {
        return "/app/medal/medalList";
    }

    public String client;

    public MedalListRequest setClient(String client) {
        this.client = client;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        UserInfo userInfo = SpMananger.getUserInfo();
        if (userInfo != null) {
            boolean seller = userInfo.isSeller();
            arrayMap.put("client", seller ? "seller" : "buyer");
        } else
            arrayMap.put("client", "buyer");
        return super.setParams();
    }
}
