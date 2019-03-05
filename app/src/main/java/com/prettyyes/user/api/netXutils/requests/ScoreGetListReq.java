package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.ScoreGetEntity;

import java.util.List;

/**
 * Created by chengang on 2017/12/27.
 */

public class ScoreGetListReq extends BaseRequest<List<ScoreGetEntity>> {
    @Override
    public String setExtraUrl() {
        return "/app/user/howScoreList";
    }
}
