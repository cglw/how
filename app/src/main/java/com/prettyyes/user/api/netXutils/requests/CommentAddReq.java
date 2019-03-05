package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.CommentAddRes;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/12.
 */

public class CommentAddReq extends BaseRequest<CommentAddRes> {


    private String comment;
    private String comment_id;
    private String answer_id;
    private String comment_type;

    public CommentAddReq setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentAddReq setComment_id(String comment_id) {
        this.comment_id = comment_id;
        return this;
    }

    public CommentAddReq setComment_type(String comment_type) {
        this.comment_type = comment_type;
        return this;
    }

    public CommentAddReq setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("comment", comment);
        arrayMap.put("comment_id", comment_id);
        arrayMap.put("answer_id", answer_id);
        arrayMap.put("comment_type", comment_type);
        return super.setParams();
    }

    @Override
    public String setExtraUrl() {
        return "/app/comment/addComment";
    }
}
