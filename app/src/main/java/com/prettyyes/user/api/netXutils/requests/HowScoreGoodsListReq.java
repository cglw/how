package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.HowScoreGoodsListRes;

import java.util.HashMap;

/**
 * Created by chengang on 2018/1/31.
 */

public class HowScoreGoodsListReq extends BaseRequest<HowScoreGoodsListRes> {
    @Override
    public String setExtraUrl() {
        return "/app/howScore/getSpuList";
    }
    private int page;

    public HowScoreGoodsListReq setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("page",page);
        return super.setParams();
    }
}
