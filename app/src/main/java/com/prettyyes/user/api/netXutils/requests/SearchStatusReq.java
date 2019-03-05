package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.SearchStatusRes;

/**
 * Created by chengang on 2017/12/28.
 */

public class SearchStatusReq extends BaseRequest<SearchStatusRes> {
    @Override
    public String setExtraUrl() {
        return "/app/system/searchStatus";
    }
}
