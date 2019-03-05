package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/11.
 */

public class HomeQueEntity extends QuestionEntity {

    private AnswerInfoEntity answer_info;
   private AnswerInfoEntity my_answer_info;

    public AnswerInfoEntity getAnswer_info() {
        return answer_info;
    }

    public void setAnswer_info(AnswerInfoEntity answer_info) {
        this.answer_info = answer_info;
    }

    public AnswerInfoEntity getMy_answer_info() {
        return my_answer_info;
    }

    public void setMy_answer_info(AnswerInfoEntity my_answer_info) {
        this.my_answer_info = my_answer_info;
    }
}
