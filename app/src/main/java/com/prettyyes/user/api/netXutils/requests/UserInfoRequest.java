package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.user.UserInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2017/8/3.
 */

public class UserInfoRequest extends BaseRequest<UserInfo> {
    @Override
    public String setExtraUrl() {
        return "/app/user/info";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("msg_device_token", SpMananger.getClien_id());
        return super.setParams();
    }
}
