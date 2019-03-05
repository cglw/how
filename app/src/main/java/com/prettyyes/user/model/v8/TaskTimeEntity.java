package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2018/1/18.
 */

public class TaskTimeEntity {
    private String task_id;
    private long time_enter;
    private long time_out;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public long getTime_enter() {
        return time_enter;
    }

    public void setTime_enter(long time_enter) {
        this.time_enter = time_enter;
    }

    public long getTime_out() {
        return time_out;
    }

    public void setTime_out(long time_out) {
        this.time_out = time_out;
    }
}
