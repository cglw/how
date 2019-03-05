package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/5.
 */

public class SpudelRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/spu/del";
    }

    private String module_id;
    private String spu_type;

    public SpudelRequest setModule_id(String module_id) {
        this.module_id = module_id;
        return this;
    }

    public SpudelRequest setSpu_type(String spu_type) {
        this.spu_type = spu_type;
        return this;
    }



    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("module_id", module_id);
        arrayMap.put("spu_type", spu_type);
        return super.setParams();
    }
}
