package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/17.
 */

public class AskUnreadEvent {
    private int position;
    private String task_id;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTask_id() {
        return task_id;
    }

    public AskUnreadEvent setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public AskUnreadEvent(int position) {
        this.position = position;
    }
}
