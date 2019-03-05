package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/3.
 */

public class TaskFilterRequest extends BaseRequest<Object> {


    @Override
    public String setExtraUrl() {
        return "/app/home/taskFilter";
    }

    private String task_id;

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("task_id", task_id);
        return super.setParams();
    }

    public String getTask_id() {
        return task_id;
    }

    public TaskFilterRequest setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }
}
