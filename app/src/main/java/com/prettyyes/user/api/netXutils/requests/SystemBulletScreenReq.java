package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.system.SystemBulletScreen;

import java.util.HashMap;

/**
 * Created by chengang on 2018/2/1.
 */

public class SystemBulletScreenReq extends BaseRequest<SystemBulletScreen> {

    private String type;
    private String act_id;

    public SystemBulletScreenReq setTypeAct() {
        type = "act";
        return this;
    }

    public SystemBulletScreenReq setAct_id(String act_id) {
        this.act_id = act_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", "question");
        arrayMap.put("act_id", act_id);
        return super.setParams();
    }

    @Override
    public String setExtraUrl() {
        if ("act".equals(type))
            return "/app/act/bulletscreen";
        else
            return "/app/system/bulletscreen";
    }
}
