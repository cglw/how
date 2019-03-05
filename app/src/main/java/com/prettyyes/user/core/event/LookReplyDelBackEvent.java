package com.prettyyes.user.core.event;

import com.prettyyes.user.model.v8.AnswerInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/8/28.
 */

public class LookReplyDelBackEvent {

    public List<AnswerInfoEntity>datas;
    public int page;
    public int position;
    public String task_id;

    public LookReplyDelBackEvent setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public LookReplyDelBackEvent setPage(int page) {
        this.page = page;
        return this;
    }

    public LookReplyDelBackEvent setPosition(int position) {
        this.position = position;
        return this;

    }

    public LookReplyDelBackEvent setDatas(List<AnswerInfoEntity> datas) {
        this.datas = datas;
        return this;
    }
}
