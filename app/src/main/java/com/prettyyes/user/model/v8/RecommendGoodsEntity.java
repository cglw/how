package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/3.
 */

public class RecommendGoodsEntity extends BaseModel {

    private String goods_id;
    private String goods_image;
    private String goods_name;
    private String goods_desc;
    private String goods_price;
    private String goods_link;
    private String create_time;
    private String status;
    private String gift;
    private String param;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_link() {
        return goods_link;
    }

    public void setGoods_link(String goods_link) {
        this.goods_link = goods_link;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
