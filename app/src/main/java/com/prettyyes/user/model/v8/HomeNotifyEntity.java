package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/11.
 */

public class HomeNotifyEntity {


    /**
     * new_task_count : 0
     * new_task_text : string
     * last_time : string
     */

    private int new_task_count;
    private String new_task_text;
    private String last_time;

    public int getNew_task_count() {
        return new_task_count;
    }

    public void setNew_task_count(int new_task_count) {
        this.new_task_count = new_task_count;
    }

    public String getNew_task_text() {
        return new_task_text;
    }

    public void setNew_task_text(String new_task_text) {
        this.new_task_text = new_task_text;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }
}
