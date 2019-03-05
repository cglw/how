package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.UserMonthScoreDetailRes;

/**
 * Created by chengang on 2017/7/21.
 */

public class UserMonthScoreDetailRequest extends BaseRequest<UserMonthScoreDetailRes> {
       @Override
    public String setExtraUrl() {
        return "/app/user/userMonthScoreDetail";
    }
}
