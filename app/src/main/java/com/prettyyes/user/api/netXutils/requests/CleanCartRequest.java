package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

/**
 * Created by chengang on 2017/7/3.
 */

public class CleanCartRequest extends BaseRequest<String> {

    @Override
    public String setExtraUrl() {
        return "/app/cart/cleanCart";
    }
}
