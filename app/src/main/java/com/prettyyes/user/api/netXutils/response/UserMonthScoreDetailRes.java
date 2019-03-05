package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.ScoreEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class UserMonthScoreDetailRes extends BaseRes {

    /**
     * last_month_score : 0
     * this_month_score : 0
     */

    private String last_month_score;
    private String this_month_score;
    private List<ScoreEntity>last_month;
    private List<ScoreEntity>this_month;
    private List<ScoreEntity>diff;

    public List<ScoreEntity> getDiff() {
        return diff;
    }

    public List<ScoreEntity> getLast_month() {
        return last_month;
    }

    public List<ScoreEntity> getThis_month() {
        return this_month;
    }

    public String getLast_month_score() {
        return last_month_score;
    }

    public String getThis_month_score() {
        return this_month_score;
    }

}
