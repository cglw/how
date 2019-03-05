package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.Suit.SkuCollectEntity;

import java.util.List;

/**
 * Created by chengang on 2017/8/16.
 */

public class SkuCollectRes {
    private List<SkuCollectEntity>data;

    public List<SkuCollectEntity> getData() {
        return data;
    }

    public void setData(List<SkuCollectEntity> data) {
        this.data = data;
    }
}
