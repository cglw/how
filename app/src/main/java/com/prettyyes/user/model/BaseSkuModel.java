package com.prettyyes.user.model;

/**
 * Created by chengang on 2017/6/29.
 */

public class BaseSkuModel {
    //base 属性
    private double price;//价格
    private long stock;//库存

    public BaseSkuModel(double price, long stock) {
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
