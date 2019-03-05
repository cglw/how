package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.applocal.AttrVaule;

import java.util.LinkedHashMap;

/**
 * Created by chengang on 2017/7/2.
 */

public class AttrListBean extends BaseModel{
    private String attr_id;
    private String attr_name;
    private LinkedHashMap<String,AttrVaule>values;

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

    public LinkedHashMap<String, AttrVaule> getValues() {
        return values;
    }

    public void setValues(LinkedHashMap<String, AttrVaule> values) {
        this.values = values;
    }
}
