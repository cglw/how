package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;

/**
 * Created by chengang on 2017/12/27.
 */

public class ScoreGetEntity  implements BaseType {
    /**
     * score : 1
     * score_type : login
     * text : 每日登录
     * sub_head :
     * rule : {"type":"login"}
     */

    private String score;
    private String score_type;
    private String text;
    private String sub_head;
    private String rule;
    private String client;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore_type() {
        return score_type;
    }

    public void setScore_type(String score_type) {
        this.score_type = score_type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSub_head() {
        return sub_head;
    }

    public void setSub_head(String sub_head) {
        this.sub_head = sub_head;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
