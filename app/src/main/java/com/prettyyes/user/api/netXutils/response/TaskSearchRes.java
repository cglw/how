package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.app.mvpPresenter.QuestionEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class TaskSearchRes extends BaseRes{

    private QuestionEntity task;

    public QuestionEntity getTask() {
        return task;
    }

    public void setTask(QuestionEntity task) {
        this.task = task;
    }
}
