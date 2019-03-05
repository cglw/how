package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/13.
 */

public class MineAskEntity extends QuestionEntity {


    private String moment_module_id;
    private String moment_type;
    private String moment_id;
    private String created_at;

    private CommentParentEntity parent;

    public CommentParentEntity getParent() {
        return parent;
    }

    public void setParent(CommentParentEntity parent) {
        this.parent = parent;
    }


    public String getMoment_module_id() {
        return moment_module_id;
    }

    public void setMoment_module_id(String moment_module_id) {
        this.moment_module_id = moment_module_id;
    }

    public String getMoment_type() {
        return moment_type;
    }

    public void setMoment_type(String moment_type) {
        this.moment_type = moment_type;
    }

    public String getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(String moment_id) {
        this.moment_id = moment_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
