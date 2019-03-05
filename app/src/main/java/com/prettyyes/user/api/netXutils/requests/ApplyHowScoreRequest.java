package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

/**
 * Created by chengang on 2017/9/25.
 */

public class ApplyHowScoreRequest extends BaseRequest<Object>{
    @Override
    public String setExtraUrl() {
        return "/app/user/applyHowScore";
    }
}
