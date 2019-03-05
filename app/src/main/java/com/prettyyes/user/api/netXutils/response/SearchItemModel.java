package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;
import com.prettyyes.user.model.v8.TopicEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/17.
 */

public class SearchItemModel extends BaseRes{
    private QuestionEntity task;
    private List<AnswerInfoEntity> answer_list;
    private SellerInfoEntity seller;
    private com.prettyyes.user.model.v8.TopicEntity topic;

    public QuestionEntity getTask() {
        return task;
    }

    public void setTask(QuestionEntity task) {
        this.task = task;
    }

    public List<AnswerInfoEntity> getAnswer_list() {
        return answer_list;
    }

    public void setAnswer_list(List<AnswerInfoEntity> answer_list) {
        this.answer_list = answer_list;
    }

    public SellerInfoEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerInfoEntity seller) {
        this.seller = seller;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }
}
