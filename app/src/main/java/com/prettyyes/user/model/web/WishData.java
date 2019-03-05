package com.prettyyes.user.model.web;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.web
 * Author: SmileChen
 * Created on: 2016/12/6
 * Description: Nothing
 */
public class WishData {

    /**
     * cart_type : suit
     * suit_id : 5466
     * suit_unit :
     * taobao_id :
     * num : 1
     * cart_status : 1
     */

    private String cart_type;
    private String suit_id;
    private String suit_unit;
    private String taobao_id;
    private String num;
    private String cart_status;

    public String getCart_type() {
        return cart_type;
    }

    public void setCart_type(String cart_type) {
        this.cart_type = cart_type;
    }

    public String getSuit_id() {
        return suit_id;
    }

    public void setSuit_id(String suit_id) {
        this.suit_id = suit_id;
    }

    public String getSuit_unit() {
        return suit_unit;
    }

    public void setSuit_unit(String suit_unit) {
        this.suit_unit = suit_unit;
    }

    public String getTaobao_id() {
        return taobao_id;
    }

    public void setTaobao_id(String taobao_id) {
        this.taobao_id = taobao_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCart_status() {
        return cart_status;
    }

    public void setCart_status(String cart_status) {
        this.cart_status = cart_status;
    }
}
