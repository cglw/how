package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.response.AddTempleteRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/29.
 */

public class TaobaoUpdateRequest extends CommonRequest<AddTempleteRes> {

    @Override
    public String setExtraUrl() {
        return "/app/speed/speedUpdate";
    }

    private String taobao_url;
    private String taobao_id;
    private String shop_name;

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("taobao_url", taobao_url);
        arrayMap.put("taobao_id", taobao_id);
        arrayMap.put("shop_name", shop_name);
        return super.setParams();
    }

    public void setTaobao_url(String taobao_url) {
        this.taobao_url = taobao_url;
    }

    public void setTaobao_id(String taobao_id) {
        this.taobao_id = taobao_id;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
}
