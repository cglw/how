package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/8/28.
 */

public class BottomDoEvent {
    public String task_id;
    public String answer_id;
    public String type;

    public BottomDoEvent setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public BottomDoEvent setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
        return this;
    }

    public BottomDoEvent setType(String type) {
        this.type = type;
        return this;
    }
}
