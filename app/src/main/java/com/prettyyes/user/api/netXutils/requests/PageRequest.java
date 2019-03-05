package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/27.
 */

public abstract class PageRequest<T> extends BaseRequest<T> {

    public int page;

    public PageRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page",page);
        return super.setParams();
    }
}
