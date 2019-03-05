package com.prettyyes.user.model.task;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/4/10.
 */

public class HomeTask extends BaseModel {

    private List<TaskHomeTask> list;

    public List<TaskHomeTask> getList() {
        return list;
    }

    public void setList(List<TaskHomeTask> list) {
        this.list = list;
    }

    private Data notice;

    public Data getNotice() {
        return notice;
    }

    public void setNotice(Data notice) {
        this.notice = notice;
    }

    public static class Data extends BaseModel {

        private String type;
        private String new_task_count;
        private String last_time;
        private String new_task_text;

        public String getNew_task_text() {
            return new_task_text;
        }

        public void setNew_task_text(String new_task_text) {
            this.new_task_text = new_task_text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNew_task_count() {
            return new_task_count;
        }

        public void setNew_task_count(String new_task_count) {
            this.new_task_count = new_task_count;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }
    }

}
