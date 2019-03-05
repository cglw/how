package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.RSAUtils;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.user.UserInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/19.
 */

public class LoginByNormalReq extends BaseRequest<UserInfo> {
    @Override
    public String setExtraUrl() {
        return "/app/user/login";
    }

    private String username;
    private String password;


    public LoginByNormalReq setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginByNormalReq setPassword(String password) {
        this.password = RSAUtils.encrpyByRsa(password);
        return this;

    }


    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("username", username);
        arrayMap.put("password", password);
        arrayMap.put("deviceToken", SpMananger.getClien_id());
        arrayMap.put("source_uid", DataCenter.getSource_uid());
        return super.setParams();
    }
}
