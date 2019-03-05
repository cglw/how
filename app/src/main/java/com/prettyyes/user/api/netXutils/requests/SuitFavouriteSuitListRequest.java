package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.SkuCollectRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/25.
 */

public class SuitFavouriteSuitListRequest extends BaseRequest<SkuCollectRes>{

    @Override
    public String setExtraUrl() {
        return "/app/client/favouriteSpuList";
    }
    private int page;

    public SuitFavouriteSuitListRequest setPage(int page) {
        this.page = page;
        return this;

    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page",page);
        return super.setParams();
    }
}
