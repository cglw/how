package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.CountCommonEntity;

/**
 * Created by chengang on 2018/1/19.
 */

public class InvateMeUnreadReq extends BaseRequest<CountCommonEntity> {
    @Override
    public String setExtraUrl() {
        return "/app/task/atTipCount";
    }
}
