package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/31.
 */

public class ReadCommentRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/comment/readComment";
    }

    private String comment_id;

    public ReadCommentRequest setComment_id(String comment_id) {
        this.comment_id = comment_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("comment_id", comment_id);
        return super.setParams();
    }
}
