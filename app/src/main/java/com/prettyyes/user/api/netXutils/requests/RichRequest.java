package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.RichesRes;

/**
 * Created by chengang on 2017/7/24.
 */

public class RichRequest extends BaseRequest<RichesRes> {

    @Override
    public String setExtraUrl() {
        return "/app/sellerorder/riches";
    }
}
