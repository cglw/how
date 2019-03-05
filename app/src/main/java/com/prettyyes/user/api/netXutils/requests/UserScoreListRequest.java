package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.ScoreEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class UserScoreListRequest extends BaseRequest<List<ScoreEntity>> {

    @Override
    public String setExtraUrl() {
        return "/app/user/userScoreList";
    }
}
