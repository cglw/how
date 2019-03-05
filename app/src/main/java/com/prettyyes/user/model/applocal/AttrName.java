package com.prettyyes.user.model.applocal;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/28.
 */

public class AttrName extends BaseModel {
    private String attr_name = "";//规格名称
    private String attr_id;
    private boolean isSelected = true;
    private int index;
    private String sku_id = "";
    private String sku_no = "";
    private String sku_price = "";
    private String sku_store = "";


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

    private List<AttrVaule> values;//规格属性


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(String attr_id) {
        this.attr_id = attr_id;
    }


    public String getName() {
        return attr_name;
    }

    public void setName(String name) {
        this.attr_name = name;
    }

    public List<AttrVaule> getValues() {
        return values;
    }

    public boolean isHaveSlectOne() {
        if (values == null)
            return true;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).isSelect())
                return true;
        }
        return false;

    }

    public void setSlectOnlyOne() {
        if (values == null)
            return;
        if (values.size() == 1) {
            values.get(0).setSelect(true);
        }

    }

    public AttrVaule getSelectValue() {
        if (values == null)
            return null;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).isSelect())
                return values.get(i);
        }
        return null;
    }

    public void setValues(List<AttrVaule> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "AttrName{" +
                "attr_name='" + attr_name + '\'' +
                ", attr_id='" + attr_id + '\'' +
                ", isSelected=" + isSelected +
                ", index=" + index +
                ", sku_id='" + sku_id + '\'' +
                ", sku_no='" + sku_no + '\'' +
                ", sku_price='" + sku_price + '\'' +
                ", sku_store='" + sku_store + '\'' +
                ", values=" + values +
                '}';
    }
}
