package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/6/27.
 */

public class PriceEvent {
    private String goods_price;
    private String freight_price;

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(String freight_price) {
        this.freight_price = freight_price;
    }
}
