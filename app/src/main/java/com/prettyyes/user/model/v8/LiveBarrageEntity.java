package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/9/20.
 */

public class LiveBarrageEntity {
    private String show_txt;
    private String type;
    private String task_id;
    private String que;
    private String answer_id;
    private String seller_id;

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getQue() {
        return que;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setShow_txt(String show_txt) {
        this.show_txt = show_txt;
    }

    public String getShow_txt() {
        return show_txt;
    }

    public String getType() {
        return type;
    }
}
