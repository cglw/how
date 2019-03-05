package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chengang on 2017/7/10.
 */

public class BrandSpuListRequest extends BaseRequest<List<SpuInfoEntity>> {

    @Override
    public String setExtraUrl() {
        return "/app/brand/brandSpuList";
    }

    private String brand_id;
    private int page;

    public BrandSpuListRequest setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("brand_id", brand_id);
        arrayMap.put("page", page);
        return super.setParams();
    }

    public BrandSpuListRequest(String brand_id) {
        this.brand_id = brand_id;
    }
}
