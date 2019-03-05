package com.prettyyes.user.core.event;

import com.prettyyes.user.model.v8.SkuSearchEntity;

/**
 * Created by chengang on 2017/6/28.
 */

public class TaobaoSelectUrlEvent {
    private SkuSearchEntity skuSearchEntity;

    public SkuSearchEntity getSkuSearchEntity() {
        return skuSearchEntity;
    }

    public void setSkuSearchEntity(SkuSearchEntity skuSearchEntity) {
        this.skuSearchEntity = skuSearchEntity;
    }

    public TaobaoSelectUrlEvent(SkuSearchEntity skuSearchEntity) {
        this.skuSearchEntity = skuSearchEntity;
    }
}
