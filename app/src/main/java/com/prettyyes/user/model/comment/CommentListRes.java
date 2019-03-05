package com.prettyyes.user.model.comment;

import java.util.List;

/**
 * Created by chengang on 2017/5/16.
 */

public class CommentListRes {
    private int comment_count;
    private List<CommentList> list;

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public List<CommentList> getList() {
        return list;
    }

    public void setList(List<CommentList> list) {
        this.list = list;
    }
}
