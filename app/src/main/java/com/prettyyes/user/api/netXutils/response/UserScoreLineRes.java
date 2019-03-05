package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.ScoreEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class UserScoreLineRes extends BaseRes {
    private List<ScoreEntity>list;

    public List<ScoreEntity> getList() {
        return list;
    }
}
