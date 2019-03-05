package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/21.
 */

public class ScoreEntity extends BaseModel implements BaseType {

    /**
     * log_month : string
     * create_time : string
     * score_type : string
     * score : string
     * content : string
     */

    private String log_month;
    private String create_time;
    private String score_type;
    private String score;
    private String content;
    private String date_time;
    private String score_rule;
    private String count;

    public String getCount() {
        return "x" + count;
    }

    public int getCountNum() {

        return Integer.parseInt(count);
    }

    public String getScore_rule() {
        return score_rule;
    }

    public void setScore_rule(String score_rule) {
        this.score_rule = score_rule;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getLog_month() {
        return log_month;
    }

    public void setLog_month(String log_month) {
        this.log_month = log_month;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getScore_type() {
        return score_type;
    }

    public void setScore_type(String score_type) {
        this.score_type = score_type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
