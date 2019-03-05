package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.CommentChildBaseEntity;

import java.util.List;

/**
 * Created by chengang on 2018/1/15.
 */

public class CommentNewListRes {
    private int comment_count;
    private List<CommentChildBaseEntity> list;


    public List<CommentChildBaseEntity> getList() {
        return list;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }
}
