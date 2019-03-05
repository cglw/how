package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.KolReplyRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/10.
 */

public class KolReplyRequest extends BaseRequest<KolReplyRes> {

    @Override
    public String setExtraUrl() {
        return "/app/task/rob";
    }

    private String answer_type;
    private String reason;
    private String task_id;
    private String module_id;
    private String answer_img_video;

    public KolReplyRequest setAnswer_img_video(String answer_img_video) {
        this.answer_img_video = answer_img_video;
        return this;
    }

    public KolReplyRequest setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
        return this;
    }

    public KolReplyRequest setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public KolReplyRequest setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public KolReplyRequest setModule_id(String module_id) {
        this.module_id = module_id;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("answer_img_video", answer_img_video);
        arrayMap.put("answer_type", answer_type);
        arrayMap.put("reason", reason);
        arrayMap.put("task_id", task_id);
        arrayMap.put("module_id", module_id);
        return super.setParams();
    }
}
