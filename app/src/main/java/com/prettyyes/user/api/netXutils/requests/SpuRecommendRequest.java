package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class SpuRecommendRequest extends BaseRequest<List<SpuInfoEntity>> {

    @Override
    public String setExtraUrl() {
        return "/app/spu/recommend";
    }

    private String module_id;
    private String spu_type;

    public SpuRecommendRequest setModule_id(String module_id) {
        this.module_id = module_id;
        return this;
    }

    public SpuRecommendRequest setSpu_type(String spu_type) {
        this.spu_type = spu_type;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("module_id",module_id);
        arrayMap.put("spu_type",spu_type);

        return super.setParams();
    }
}
