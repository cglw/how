package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.MonthScoreRes;

/**
 * Created by chengang on 2017/9/25.
 */

public class UserMonthScoreOfYearRequest extends BaseRequest<MonthScoreRes> {
    @Override
    public String setExtraUrl() {
        return "/app/user/userMonthScoreOfYear";
    }
}
