package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.BrandListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/10.
 */

public class BrandListRequest extends BaseRequest<BrandListRes> {

    @Override
    public String setExtraUrl() {
        return "/app/suit/brandList";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("brand_name", brand_name);
        return super.setParams();
    }

    private String brand_name;

    public BrandListRequest(String brand_name) {
        this.brand_name = brand_name;
    }


}
