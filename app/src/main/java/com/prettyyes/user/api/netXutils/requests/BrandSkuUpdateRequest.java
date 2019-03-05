package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.response.AddTempleteRes;

/**
 * Created by chengang on 2017/6/29.
 */

public class BrandSkuUpdateRequest extends CommonRequest<AddTempleteRes>  {

    @Override
    public String setExtraUrl() {
        return "/app/brandSku/brandSkuUpdate";
    }

    private String item_url;
    private String shop_name;
    private String item_id;
    private String platform;
}
