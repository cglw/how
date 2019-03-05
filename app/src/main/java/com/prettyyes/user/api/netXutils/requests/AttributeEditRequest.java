package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.api.netXutils.response.AttributeEditRes;

import java.util.HashMap;

/**
 * Created by chengang on 2017/6/28.
 */

public class AttributeEditRequest extends BaseRequest<AttributeEditRes> {

    @Override
    public String setExtraUrl() {
        return "/app/attribute/edit";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("attr_name", attr_name);
        return super.setParams();
    }

    private String cat_id;
    private String attr_name;
    private String attr_input_type;
    private String attr_values;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public AttributeEditRequest setAttr_name(String attr_name) {
        this.attr_name = attr_name;
        return this;
    }

    public String getAttr_input_type() {
        return attr_input_type;
    }

    public void setAttr_input_type(String attr_input_type) {
        this.attr_input_type = attr_input_type;
    }

    public String getAttr_values() {
        return attr_values;
    }

    public void setAttr_values(String attr_values) {
        this.attr_values = attr_values;
    }
}
