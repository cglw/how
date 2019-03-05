package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/12.
 */

public class ReplyQuestionSuccessEvent {
    private String task_id;


    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public ReplyQuestionSuccessEvent(String task_id) {
        this.task_id = task_id;
    }
}
