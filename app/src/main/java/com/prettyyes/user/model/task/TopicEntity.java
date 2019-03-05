package com.prettyyes.user.model.task;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/2/22.
 */

public class TopicEntity extends BaseModel {
   List<TaskHomeTask>list;

    public List<TaskHomeTask> getList() {
        return list;
    }

    public void setList(List<TaskHomeTask> list) {
        this.list = list;
    }
}
