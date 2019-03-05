package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.CollectListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/6.
 */

public class CollectSpuListRequest extends BaseRequest<CollectListRes> {

    @Override
    public String setExtraUrl() {
        return "/app/client/favouriteList";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        return super.setParams();
    }

    int page;

    public CollectSpuListRequest(int page) {
        this.page = page;
    }
}
