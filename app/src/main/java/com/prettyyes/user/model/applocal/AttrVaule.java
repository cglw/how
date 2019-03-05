package com.prettyyes.user.model.applocal;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/6/30.
 */

public class AttrVaule extends BaseModel{
//     "attr_value_id": 0,
//             "attr_value": "string"
//
    private String attr_value_id;
    private String attr_value;
    private boolean isSelect=false;

    public String getAttr_value_id() {
        return attr_value_id;
    }

    public void setAttr_value_id(String attr_value_id) {
        this.attr_value_id = attr_value_id;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }


    @Override
    public String toString() {
        return "AttrVaule{" +
                "attr_value_id='" + attr_value_id + '\'' +
                ", attr_value='" + attr_value + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
