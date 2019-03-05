package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.HowScoreEntity;

/**
 * Created by chengang on 2017/9/25.
 */

public class HowScoreRequest extends BaseRequest<HowScoreEntity> {
    @Override
    public String setExtraUrl() {
        return "/app/user/getHowScore";
    }
}
