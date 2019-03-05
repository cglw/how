package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/12/18.
 */

public class TaskAddRequest extends BaseRequest<Object> {
    @Override
    public String setExtraUrl() {
        return "/app/task/add";
    }

    private String desc;
    private String pic_list;
    private String scene;
    private String task_function;
    private String is_open = "1";
    private String at_users;
    private String act_id;
    private String topic_id;


    public TaskAddRequest setDesc(String desc) {
        this.desc = desc;
        return this;


    }

    public TaskAddRequest setPic_list(String pic_list) {
        this.pic_list = pic_list;
        return this;

    }

    public TaskAddRequest setAt_users(String at_users) {
        this.at_users = at_users;
        return this;

    }

    public TaskAddRequest setAct_id(String act_id) {
        this.act_id = act_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("desc",desc);
        arrayMap.put("pic_list",pic_list);
        arrayMap.put("scene",scene);
        arrayMap.put("task_function",task_function);
        arrayMap.put("is_open",is_open);
        arrayMap.put("at_users",at_users);
        arrayMap.put("act_id",act_id);
        arrayMap.put("topic_id",topic_id);
        return super.setParams();
    }
}