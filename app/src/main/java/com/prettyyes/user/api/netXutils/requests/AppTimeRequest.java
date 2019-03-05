package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.AppTimeRes;

/**
 * Created by chengang on 2017/7/7.
 */

public class AppTimeRequest extends BaseRequest<AppTimeRes>{


    @Override
    public String setExtraUrl() {
        return "/app/time";
    }

}
