package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.SkuSearchEntity;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/28.
 */

public class SpeedSearchRequest extends BaseRequest<SkuSearchEntity> {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public SpeedSearchRequest(String url) {
        this.url = url;
    }


    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("url", url);
        return super.setParams();
    }

    @Override
    public String setExtraUrl() {
        return "/app/speed/speedSearch";
    }
}
