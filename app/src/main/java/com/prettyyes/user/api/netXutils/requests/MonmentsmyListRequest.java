package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.MonmentsmyListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/13.
 */

public class MonmentsmyListRequest extends BaseRequest<MonmentsmyListRes> {


    @Override
    public String setExtraUrl() {
        return "/app/moments/myList";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page",page);
        arrayMap.put("moment_type",moment_type);
        return super.setParams();
    }

    public MonmentsmyListRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public MonmentsmyListRequest setMoment_type(String moment_type) {
        this.moment_type = moment_type;
        return this;
    }

    private String moment_type;
    private int page;

}
