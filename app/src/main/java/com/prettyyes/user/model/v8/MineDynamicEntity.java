package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.core.SpMananger;

/**
 * Created by chengang on 2017/7/13.
 */

public class MineDynamicEntity extends AnswerInfoEntity implements BaseType {
    private String moment_module_id;
    private String moment_type;
    private String moment_id;
    private long created_at_timestamp;
    private String comment;//评论
    private String comment_type;//评论
    private String comment_id;
    private String parent_id;
    private CommentParentEntity parent;
    private QuestionEntity task;
    private String comment_status;
    public String page_type;

    public boolean isMe() {
        if (SpMananger.getUid() == null)
            return false;
        if (getTask() == null)
            return false;
        return SpMananger.getUid().equals(getTask().getUid());
    }

    private String item_type = "invate_me";

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public void setTypeMyAsk() {
        this.item_type = "myask";
    }

    public void setInvateMe() {
        this.item_type = "invate_me";
    }

    public boolean isMyAsk() {
        return "myask".equals(item_type);
    }

    public boolean isInvateMe() {
        return "invate_me".equals(item_type);
    }


    public void setPage_type(String page_type) {
        this.page_type = page_type;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public long getCreated_at_timestamp() {
        return created_at_timestamp;
    }

    public void setCreated_at_timestamp(long created_at_timestamp) {
        this.created_at_timestamp = created_at_timestamp;
    }

    public QuestionEntity getTask() {
        return task;
    }

    public void setTask(QuestionEntity task) {
        this.task = task;
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


    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
