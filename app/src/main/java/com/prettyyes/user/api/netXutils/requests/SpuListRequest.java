package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.SpuListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/29.
 */

public class SpuListRequest extends BaseRequest<SpuListRes> {
    private int page;
    private String spu_type;
    private String spu_value;


    @Override
    public String setExtraUrl() {
        return "/app/spu/getSpuListByType";
    }

    public SpuListRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page", page);
        arrayMap.put("s_value", spu_value);
        arrayMap.put("spu_type", spu_type);
        return super.setParams();
    }

    public SpuListRequest setSpu_type(String spu_type) {
        this.spu_type = spu_type;
        return this;

    }

    public SpuListRequest setSpu_value(String spu_value) {
        this.spu_value = spu_value;
        return this;
    }
}
