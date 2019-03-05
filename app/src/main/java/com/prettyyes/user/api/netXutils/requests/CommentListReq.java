package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.CommentNewListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/12.
 */

public class CommentListReq extends BaseRequest<CommentNewListRes> {

    private String answer_id;
    private String comment_type;
    private int page;

    @Override
    public String setExtraUrl() {
        return "/app/comment/commentList";
    }

    public CommentListReq setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
        return this;
    }

    public CommentListReq setComment_type(String comment_type) {
        this.comment_type = comment_type;
        return this;
    }

    public CommentListReq setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("answer_id", answer_id);
        arrayMap.put("comment_type", comment_type);
        arrayMap.put("page", page);
        return super.setParams();
    }
}
