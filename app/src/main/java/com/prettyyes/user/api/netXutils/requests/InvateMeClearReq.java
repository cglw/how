package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/19.
 */

public class InvateMeClearReq extends BaseRequest<Object> {
    @Override
    public String setExtraUrl() {
        return "/app/task/clearAtTipCount";
    }

    private String task_id;

    public InvateMeClearReq setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("task_id", task_id);
        return super.setParams();
    }
}
