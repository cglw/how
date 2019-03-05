package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/17.
 */

public class InvateKolRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/task/kolTaskInvite";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("invite_users",invite_users);
        arrayMap.put("task_id",task_id);
        return super.setParams();
    }

    private String invite_users;
    private String task_id;

    public InvateKolRequest(String invite_users, String task_id) {
        this.invite_users = invite_users;
        this.task_id = task_id;
    }

}
