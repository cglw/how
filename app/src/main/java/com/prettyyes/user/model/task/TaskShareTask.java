package com.prettyyes.user.model.task;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.task
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class TaskShareTask extends BaseModel{

    /**
     * url : http://test.prettyyes.com/main_questions/share?task_id=5621
     * task : 想要黑色一字领连衣裙想要黑色一字领连衣裙想要黑色一字领连衣裙想要黑色一字领连衣裙想要黑色一字领连衣裙想要黑色一字领连衣裙想要黑色一字领连衣裙想要黑色一字领连衣裙
     * task_img : http://img.prettyyes.com/system/head_w.png
     * headimg : http://img.prettyyes.com/system/head_w.png
     * nickname : py_1459042261
     */

    private String url;
    private String task;
    private String task_img;
    private String headimg;
    private String nickname;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTask_img(String task_img) {
        this.task_img = task_img;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrl() {
        return url;
    }

    public String getTask() {
        return task;
    }

    public String getTask_img() {
        return task_img;
    }

    public String getHeadimg() {
        return headimg;
    }

    public String getNickname() {
        return nickname;
    }
}
