package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.UserScoreLineRes;

/**
 * Created by chengang on 2017/7/21.
 */

public class UserScoreLineRequest extends BaseRequest<UserScoreLineRes> {

    @Override
    public String setExtraUrl() {
        return "/app/user/userScoreLineChart";
    }
}
