package com.prettyyes.user.model.v8;

import java.io.Serializable;

/**
 * Created by chengang on 2017/6/18.
 */

public class CartInfoEntity implements Serializable {


    /**
     * sku_id : 17079
     * uid : 1085
     * module_id : 39576
     * sku_type : suit
     * sku_name : 测试
     * main_img : http://img.prettyyes.com/seller-laravel--4670-1481789250.jpeg
     * small_img :
     * sku_price : 11.00
     * express_price : 0
     * share_status : 0
     * buy : 1
     * suit_id : 12432
     * source_info : {"answer_id":0,"seller_id":1085}
     * num : 1
     * cart_id : 1491
     * cart_status : 1
     */

    private String spu_id;
    private String uid;
    private String module_id;
    private String spu_type;
    private String spu_name;
    private String main_img;
    private String small_img;
    private String spu_price;
    private String express_price;
    private String share_status;
    private int buy;
    private String suit_id;
    private String source_info;
    private int num;
    private String cart_id;
    private int cart_status;
    private String attr_value_text;

    public String getSpu_type() {
        return spu_type;
    }

    public void setSpu_type(String spu_type) {
        this.spu_type = spu_type;
    }

    public String getAttr_value_text() {
        return attr_value_text;
    }

    public void setAttr_value_text(String attr_value_text) {
        this.attr_value_text = attr_value_text;
    }

    public String getSku_id() {
        return spu_id;
    }

    public void setSku_id(String sku_id) {
        this.spu_id = sku_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getSku_type() {
        return spu_type;
    }

    public void setSku_type(String sku_type) {
        this.spu_type = sku_type;
    }

    public String getSku_name() {
        return spu_name;
    }

    public void setSku_name(String sku_name) {
        this.spu_name = sku_name;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getSmall_img() {
        return small_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public String getSku_price() {
        return spu_price;
    }

    public void setSku_price(String sku_price) {
        this.spu_price = sku_price;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getShare_status() {
        return share_status;
    }

    public void setShare_status(String share_status) {
        this.share_status = share_status;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public String getSuit_id() {
        return suit_id;
    }

    public void setSuit_id(String suit_id) {
        this.suit_id = suit_id;
    }

    public String getSource_info() {
        return source_info;
    }

    public void setSource_info(String source_info) {
        this.source_info = source_info;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_status() {
        return cart_status;
    }

    public void setCart_status(int cart_status) {
        this.cart_status = cart_status;
    }
}
