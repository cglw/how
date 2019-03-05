package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.CollectRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/6.
 */

public class CollectRequest extends BaseRequest<CollectRes> {


    @Override
    public String setExtraUrl() {
        return "/app/client/favourite";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("module_id", module_id);
        arrayMap.put("type", spu_type);
        arrayMap.put("is_like", is_like);
        return super.setParams();
    }

    public CollectRequest setIs_like(String is_like) {
        this.is_like = is_like;
        return this;
    }

    public CollectRequest setModule_id(String module_id) {
        this.module_id = module_id;
        return this;
    }

    public CollectRequest setSpu_type(String spu_type) {
        this.spu_type = spu_type;
        return this;
    }

    private String is_like;
    private String module_id;
    private String spu_type;

}
