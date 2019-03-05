package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.CommentChildRes;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/14.
 */

public class CommentChildReq extends BaseRequest<CommentChildRes> {
    @Override
    public String setExtraUrl() {
        return "/app/comment/commentInfoAndChildren";
    }

    private String comment_id;
    private int page;



    public CommentChildReq setComment_id(String comment_id) {
        this.comment_id = comment_id;
        return this;
    }

    public CommentChildReq setPage(int page) {
        this.page = page;
        return this;

    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        arrayMap.put("comment_id", comment_id);
        return super.setParams();
    }
}
