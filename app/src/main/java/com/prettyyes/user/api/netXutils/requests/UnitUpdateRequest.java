package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.response.AddTempleteRes;

/**
 * Created by chengang on 2017/6/29.
 */

public class UnitUpdateRequest extends CommonRequest<AddTempleteRes> {

    @Override
    public String setExtraUrl() {
        return "/app/suit/unitUpdate";
    }



}
