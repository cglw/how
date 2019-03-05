package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/12.
 */

public class AnswerViewAddReq extends BaseRequest<Object> {
    @Override
    public String setExtraUrl() {
        return "/app/task/answerViewNum";
    }

    private String answer_id;

    public AnswerViewAddReq setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
        return this;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("answer_id", answer_id);
        return super.setParams();
    }
}
