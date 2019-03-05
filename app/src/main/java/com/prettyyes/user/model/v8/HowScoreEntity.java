package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/9/25.
 */

public class HowScoreEntity extends BaseModel{

    /**
     * how_score : 0
     * apply_score : 0
     * applyed_score : 0
     * apply_money : 0
     */

    private String how_score;
    private String apply_score;
    private String applyed_score;
    private String apply_money;

    public String getHow_score() {
        return how_score;
    }

    public void setHow_score(String how_score) {
        this.how_score = how_score;
    }

    public String getApply_score() {
        return apply_score;
    }

    public void setApply_score(String apply_score) {
        this.apply_score = apply_score;
    }

    public String getApplyed_score() {
        return applyed_score;
    }

    public void setApplyed_score(String applyed_score) {
        this.applyed_score = applyed_score;
    }

    public String getApply_money() {
        return apply_money;
    }

    public void setApply_money(String apply_money) {
        this.apply_money = apply_money;
    }
}

