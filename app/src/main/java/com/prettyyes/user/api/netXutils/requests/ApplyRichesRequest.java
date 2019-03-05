package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

/**
 * Created by chengang on 2017/7/24.
 */

public class ApplyRichesRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/applyRiches";
    }
}
