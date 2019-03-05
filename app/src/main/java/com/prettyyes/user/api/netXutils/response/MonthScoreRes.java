package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.MonthScoreEntity;

import java.util.List;

/**
 * Created by chengang on 2017/9/25.
 */

public class MonthScoreRes {
    private List<MonthScoreEntity> list;
    private String how_score;
    private String this_month_score;
    private String apply_txt;

    public String getApply_txt() {
        return apply_txt;
    }

    public String getHow_score() {
        return how_score;
    }

    public String getThis_month_score() {
        return this_month_score;
    }

    public List<MonthScoreEntity> getList() {
        return list;
    }
}
