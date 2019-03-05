package com.prettyyes.user.model.coupon;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.coupon
 * Author: SmileChen
 * Created on: 2016/11/7
 * Description: Nothing
 */
public class CouponInfoEntity extends BaseModel {
    private int code_id;
    private String code;
    private String name;
    private String content;
    private String end_time;
    private String coupon_price;
    private String coupon_txt;
    private List<String> gift_list;
    private String coupon_name;
    private String coupon_type;
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    private boolean isuse = false;

    public boolean isuse() {
        return isuse;
    }

    public void setIsuse(boolean isuse) {
        this.isuse = isuse;
    }

    public int getCode_id() {
        return code_id;
    }

    public void setCode_id(int code_id) {
        this.code_id = code_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getCoupon_txt() {
        return coupon_txt;
    }

    public void setCoupon_txt(String coupon_txt) {
        this.coupon_txt = coupon_txt;
    }

    public List<String> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<String> gift_list) {
        this.gift_list = gift_list;
    }
}
