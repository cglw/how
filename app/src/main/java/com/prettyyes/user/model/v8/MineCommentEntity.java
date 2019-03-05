package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/13.
 */

public class MineCommentEntity extends AnswerInfoEntity {


    private String moment_module_id;
    private String moment_type;
    private String moment_id;
    private String created_at;
    private String comment;//评论
    private String comment_type;//评论
    private String comment_id;
    private String parent_id;
    private CommentParentEntity parent;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

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
