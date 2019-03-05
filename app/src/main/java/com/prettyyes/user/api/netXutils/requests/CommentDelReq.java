package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/12.
 */

public class CommentDelReq extends BaseRequest<Object> {
    @Override
    public String setExtraUrl() {
        return "/app/comment/delComment";
    }
    private String comment_id;

    public CommentDelReq setComment_id(String comment_id) {
        this.comment_id = comment_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("comment_id",comment_id);
        return super.setParams();
    }
}
