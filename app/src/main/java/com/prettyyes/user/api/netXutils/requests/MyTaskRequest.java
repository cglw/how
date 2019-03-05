package com.prettyyes.user.api.netXutils.requests;


import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.v8.HomeTaskEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chengang on 2017/9/6.
 */

public class MyTaskRequest extends BaseRequest<ListCommon<List<HomeTaskEntity>>> {

    @Override
    public String setExtraUrl() {
        return "/app/task/mylist";
    }

    private int page;

    public MyTaskRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        return super.setParams();
    }
}
