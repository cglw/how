package com.prettyyes.user.model.task;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.task
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class TaskSetting extends BaseModel{

    /**
     * id : 1
     * min_value : 100
     * max_value : 1000
     * start_value : 0
     * end_value : 4000
     * update_time : 2015-09-16 23:52:31
     */

    private InfoEntity info;

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        private int id;
        private int min_value;
        private int max_value;
        private int start_value;
        private int end_value;
        private String update_time;

        public void setId(int id) {
            this.id = id;
        }

        public void setMin_value(int min_value) {
            this.min_value = min_value;
        }

        public void setMax_value(int max_value) {
            this.max_value = max_value;
        }

        public void setStart_value(int start_value) {
            this.start_value = start_value;
        }

        public void setEnd_value(int end_value) {
            this.end_value = end_value;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getId() {
            return id;
        }

        public int getMin_value() {
            return min_value;
        }

        public int getMax_value() {
            return max_value;
        }

        public int getStart_value() {
            return start_value;
        }

        public int getEnd_value() {
            return end_value;
        }

        public String getUpdate_time() {
            return update_time;
        }
    }
}
