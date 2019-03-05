package com.prettyyes.user.model.order;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.order
 * Author: SmileChen
 * Created on: 2016/10/27
 * Description: Nothing
 */
public class CartAdd {
    private String uuid;
    private String cart_type;
    private int suit_id;
    private String suit_unit;
    private String suit_taobao_id;
    private int num;
    private int cart_status;

    public CartAdd(String suit_taobao_id, String cart_type, int suit_id, String suit_unit, int num, int cart_status, String uuid) {
        this.suit_taobao_id = suit_taobao_id;
        this.cart_type = cart_type;
        this.suit_id = suit_id;
        this.suit_unit = suit_unit;
        this.num = num;
        this.cart_status = cart_status;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCart_type() {
        return cart_type;
    }

    public void setCart_type(String cart_type) {
        this.cart_type = cart_type;
    }

    public int getSuit_id() {
        return suit_id;
    }

    public void setSuit_id(int suit_id) {
        this.suit_id = suit_id;
    }

    public String getSuit_unit() {
        return suit_unit;
    }

    public void setSuit_unit(String suit_unit) {
        this.suit_unit = suit_unit;
    }

    public String getSuit_taobao_id() {
        return suit_taobao_id;
    }

    public void setSuit_taobao_id(String suit_taobao_id) {
        this.suit_taobao_id = suit_taobao_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCart_status() {
        return cart_status;
    }

    public void setCart_status(int cart_status) {
        this.cart_status = cart_status;
    }
}
