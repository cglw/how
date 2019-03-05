package com.prettyyes.user.model;

/**
 * Created by chengang on 2017/5/18.
 */

public class UnreadModel extends BaseModel {
    private int comment_unread_count;

    public int getComment_unread_count() {
        return comment_unread_count;
    }

    public void setComment_unread_count(int comment_unread_count) {
        this.comment_unread_count = comment_unread_count;
    }
}
