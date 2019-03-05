package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;

/**
 * Created by chengang on 2017/12/27.
 */

public class TaskNewUserItemEntity implements BaseType {

    /**
     * id : 1
     * content : 完成登录
     * score : 5
     * mission_complete : 0
     */

    private String id;
    private String content;
    private String score;
    private String mission_complete;
    private String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMission_complete() {
        return mission_complete;
    }

    public void setMission_complete(String mission_complete) {
        this.mission_complete = mission_complete;
    }

    public boolean isComplete() {
        return "1".equals(mission_complete);
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
