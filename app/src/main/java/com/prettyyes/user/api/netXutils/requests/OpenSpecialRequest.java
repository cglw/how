package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.OpenSpecialRes;

/**
 * Created by chengang on 2017/7/18.
 * 运营专场
 */

public class OpenSpecialRequest extends BaseRequest<OpenSpecialRes>{

    @Override
    public String setExtraUrl() {
        return "/app/rewardToken/rewardTokenList";
    }
}
