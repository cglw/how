package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/6/28.
 */

public class Attr extends BaseModel{

    private String attr_id;
    private String attr_name;
    private String attr_value;
    private String parent_attr_id="0";

    public String getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(String attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public String getParent_attr_id() {
        return parent_attr_id;
    }

    public void setParent_attr_id(String parent_attr_id) {
        this.parent_attr_id = parent_attr_id;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "attr_id='" + attr_id + '\'' +
                ", attr_name='" + attr_name + '\'' +
                ", attr_value='" + attr_value + '\'' +
                ", parent_attr_id='" + parent_attr_id + '\'' +
                '}';
    }
}
