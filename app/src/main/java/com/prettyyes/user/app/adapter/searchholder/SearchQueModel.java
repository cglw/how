package com.prettyyes.user.app.adapter.searchholder;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.search.SearchBaseEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchQueModel extends SearchBaseEntity implements BaseType {


    private List<AnswerInfoEntity> answer_list;
    private QuestionEntity task;

    @Override
    public void setType(String type) {
        super.setType("task");
    }

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

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
