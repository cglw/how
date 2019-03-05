package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.MonmentsmyListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/11.
 */

public class CollectTaskListRequest extends BaseRequest<MonmentsmyListRes> {
     @Override
    public String setExtraUrl() {
        return "/app/client/favouriteTaskList";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        return super.setParams();
    }

    private int page;

    public CollectTaskListRequest(int page) {
        this.page = page;
    }
}
