package com.prettyyes.user.model.v8;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengang on 2017/6/18.
 */

public class CartListEntity implements Serializable{

    /**
     * seller_id : 1085
     * seller_name : 嘻嘻嘻
     * seller_headimg : http://img.prettyyes.com/1085-4583-1451650313.jpeg
     * seller_ace_txt : ddddd
     * famous_type : 1
     */

    private String seller_id;
    private String seller_name;
    private String seller_headimg;
    private String seller_ace_txt;
    public String grade_title;
    private int famous_type;
    private List<CartInfoEntity> cart_list;

    public String getGrade_title() {
        return grade_title;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_headimg() {
        return seller_headimg;
    }

    public void setSeller_headimg(String seller_headimg) {
        this.seller_headimg = seller_headimg;
    }

    public String getSeller_ace_txt() {
        return seller_ace_txt;
    }

    public void setSeller_ace_txt(String seller_ace_txt) {
        this.seller_ace_txt = seller_ace_txt;
    }

    public int getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(int famous_type) {
        this.famous_type = famous_type;
    }

    public List<CartInfoEntity> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartInfoEntity> cart_list) {
        this.cart_list = cart_list;
    }
}
