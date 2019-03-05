package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.HowScoreGoods;

import java.util.List;

/**
 * Created by chengang on 2018/2/1.
 */

public class HowScoreGoodsListRes {
private List<HowScoreGoods>data;

    public List<HowScoreGoods> getData() {
        return data;
    }

    public void setData(List<HowScoreGoods> data) {
        this.data = data;
    }
}
