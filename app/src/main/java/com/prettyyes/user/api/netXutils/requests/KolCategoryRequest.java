package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.KolCategoryRes;

/**
 * Created by chengang on 2017/7/12.
 */

public class KolCategoryRequest extends BaseRequest<KolCategoryRes> {

    @Override
    protected boolean needCache() {
        return true;
    }

    @Override
    public String setExtraUrl() {
        return "/app/task/kolCategory";
    }
}
