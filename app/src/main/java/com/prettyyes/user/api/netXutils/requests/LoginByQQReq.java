package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.user.UserInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/19.
 */

public class LoginByQQReq extends BaseRequest<UserInfo> {
    @Override
    public String setExtraUrl() {
        return "/app/qq/authUMeng";
    }

    private String openid;
    private String access_token;


    public LoginByQQReq setOpenId(String openid) {
        this.openid = openid;
        return this;
    }

    public LoginByQQReq setAccess_token(String access_token) {
        this.access_token = access_token;
        return this;

    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("openid",openid);
        arrayMap.put("access_token",access_token);
        arrayMap.put("deviceToken",  SpMananger.getClien_id());
        arrayMap.put("source_uid", DataCenter.getSource_uid());
        return super.setParams();
    }
}
