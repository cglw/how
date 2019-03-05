package com.prettyyes.user.model.web;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/12/1
 * Description: Nothing
 */
public class CalendarModel {
    private String title;
    private String desc;
    private String start_time;
    private String end_time;
    private int remindtime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getRemindtime() {
        return remindtime;
    }

    public void setRemindtime(int remindtime) {
        this.remindtime = remindtime;
    }

    @Override
    public String toString() {
        return "CalendarModel{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", remindtime=" + remindtime +
                '}';
    }
}
