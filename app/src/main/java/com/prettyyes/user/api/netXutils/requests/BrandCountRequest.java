package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.BrandCountRes;

/**
 * Created by chengang on 2017/8/21.
 */

public class BrandCountRequest extends BaseRequest<BrandCountRes> {
    @Override
    public String setExtraUrl() {
        return "/app/task/shareBrandCount";
    }

    @Override
    protected boolean needCache() {
        return true;
    }
}
