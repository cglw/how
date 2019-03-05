package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/13.
 *
 * 别人评论你的
 */

public class CommentParentEntity {


    /**
     * comment_id : 0
     * created_at : string
     * updated_at : string
     * comment : string
     * answer_id : 0
     * uid : 0
     * form_uid : 0
     * username : string
     * headimg : string
     * famous_type : 0
     */

    private String comment_id;
    private String created_at;
    private String updated_at;
    private String comment;
    private String answer_id;
    private String uid;
    private String form_uid;
    private String username;
    private String headimg;
    private int famous_type;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getForm_uid() {
        return form_uid;
    }

    public void setForm_uid(String form_uid) {
        this.form_uid = form_uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(int famous_type) {
        this.famous_type = famous_type;
    }
}
