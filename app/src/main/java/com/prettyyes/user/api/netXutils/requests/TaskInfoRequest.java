package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.task.TaskInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/25.
 */

public class TaskInfoRequest extends BaseRequest<TaskInfo> {

    @Override
    public String setExtraUrl() {
        return "/app/task/taskInfo";
    }

    private int page;
    private String first_answer_id;
    private String task_id;


    public TaskInfoRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public TaskInfoRequest setFirst_answer_id(String first_answer_id) {
        this.first_answer_id = first_answer_id;
        return this;
    }


    public TaskInfoRequest setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        arrayMap.put("task_id", task_id);
        arrayMap.put("first_answer_id", first_answer_id);
        return super.setParams();
    }
}
