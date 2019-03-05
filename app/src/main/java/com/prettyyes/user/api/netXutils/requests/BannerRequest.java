package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.common.AppBanner;

import java.util.HashMap;

/**
 * Created by chengang on 2017/8/11.
 */

public class BannerRequest extends BaseRequest<AppBanner> {
    @Override
    public String setExtraUrl() {
        return "/app/banner/bannerByType";
    }

    private String banner_type;

    public BannerRequest setBanner_type(String banner_type) {
        this.banner_type = banner_type;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("banner_type", banner_type);
        return super.setParams();
    }
}
