package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/28.
 */

public class AttrParent extends BaseModel {
    private List<Attr>attr;
    private String sku_id="";
    private String sku_no="";
    private String sku_price="";
    private String sku_store="";

    public List<Attr> getAttr() {
        return attr;
    }

    public void setAttr(List<Attr> attr) {
        this.attr = attr;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_no() {
        return sku_no;
    }

    public void setSku_no(String sku_no) {
        this.sku_no = sku_no;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getSku_store() {
        return sku_store;
    }

    public void setSku_store(String sku_store) {
        this.sku_store = sku_store;
    }

    @Override
    public String toString() {
        return "AttrParent{" +
                "attr=" + attr +
                ", sku_id='" + sku_id + '\'' +
                ", sku_no='" + sku_no + '\'' +
                ", sku_price='" + sku_price + '\'' +
                ", sku_store='" + sku_store + '\'' +
                '}';
    }
}
