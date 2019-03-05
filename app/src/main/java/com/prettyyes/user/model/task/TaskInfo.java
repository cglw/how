package com.prettyyes.user.model.task;

import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.task
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class TaskInfo extends QuestionEntity {


    private String create_time;

    public String getCreate_time() {
        return create_time;
    }

    private List<AnswerInfoEntity> suit_list;

    public List<AnswerInfoEntity> getSuit_list() {
        return suit_list;
    }

    public void setSuit_list(List<AnswerInfoEntity> suit_list) {
        this.suit_list = suit_list;
    }


}
