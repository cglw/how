package com.prettyyes.user.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/6/6.
 */

public class CartItemModel implements Serializable {
    private String type = "unit";//kol 还是unit
    private String headimg;
    private String nickname;
    public String grade_title;
    private String seller_id;
    private String sku_id;
    private String module_id;
    private String spu_type;
    private String famous_type;


    private String unit_id;
    private String unit_name;
    private String price;
    private String unit_img;
    private int num = 1;
    private String cart_id;
    private int cart_status;
    private boolean select = false;//是否被选中
    private List<Unit> unit_childs;
    private boolean refreshOnly=false;
    private String attr_value_text;

    public String getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(String famous_type) {
        this.famous_type = famous_type;
    }

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public String getAttr_value_text() {
        return attr_value_text;
    }

    public void setAttr_value_text(String attr_value_text) {
        this.attr_value_text = attr_value_text;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public boolean isRefreshOnly() {
        return refreshOnly;
    }

    public void setRefreshOnly(boolean refreshOnly) {
        this.refreshOnly = refreshOnly;
    }

    public List<Unit> getUnit_childs() {
        if (unit_childs == null)
            unit_childs = new ArrayList<>();
        return unit_childs;
    }

    public String getSku_type() {
        return spu_type;
    }

    public void setSku_type(String sku_type) {
        this.spu_type = sku_type;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public void setUnit_childs(List<Unit> unit_childs) {
        this.unit_childs = unit_childs;
    }

    public static class Unit implements Serializable {

        private String cart_id;
        private int cart_status;

        public Unit(String cart_id, int cart_status) {
            this.cart_id = cart_id;
            this.cart_status = cart_status;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isKol_select() {
        boolean result = true;
        for (int i = 0; i < getUnit_childs().size(); i++) {
            result = result && (getUnit_childs().get(i).getCart_status() == 1);
        }

        return result;
    }




    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit_img() {
        return unit_img;
    }

    public void setUnit_img(String unit_img) {
        this.unit_img = unit_img;
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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
