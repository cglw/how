package com.prettyyes.user.model;

/**
 * Created by chengang on 2017/5/16.
 */

public class TaskInfoComment extends BaseModel {

    private int task_id;
    private String desc;
    private String pic_list;
    private String task_act_id;
    private String nick_name;
    private String hashTag;

    public String getTask_act_id() {
        return task_act_id;
    }

    public void setTask_act_id(String task_act_id) {
        this.task_act_id = task_act_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic_list() {
        return pic_list;
    }

    public void setPic_list(String pic_list) {
        this.pic_list = pic_list;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
}
