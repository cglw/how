package com.prettyyes.user.model.applocal;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/19.
 */

public class QueEntity {
    private String name;
    private String question;
    private ArrayList<String> que_imgs;
    private String headimg;
    private String hash_tag;
    private String topic_hash_tag;
    private String topic_id;
    private String task_act_id;

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getTopic_hash_tag() {
        return topic_hash_tag;
    }

    public void setTopic_hash_tag(String topic_hash_tag) {
        this.topic_hash_tag = topic_hash_tag;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTask_act_id() {
        return task_act_id;
    }

    public void setTask_act_id(String task_act_id) {
        this.task_act_id = task_act_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getQue_imgs() {
        return que_imgs;
    }

    public void setQue_imgs(ArrayList<String> que_imgs) {
        this.que_imgs = que_imgs;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
