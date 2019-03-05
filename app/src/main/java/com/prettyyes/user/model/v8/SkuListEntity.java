package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/7/2.
 */

public class SkuListEntity extends BaseModel {

    private List<AttrParent> attr_json;
    private List<AttrListBean> attr_list;
    private List<AttrStr> attr_str_list;

    public List<AttrParent> getAttr_json() {
        return attr_json;
    }

    public void setAttr_json(List<AttrParent> attr_json) {
        this.attr_json = attr_json;
    }

    public List<AttrListBean> getAttr_list() {
        return attr_list;
    }

    public void setAttr_list(List<AttrListBean> attr_list) {
        this.attr_list = attr_list;
    }

    public List<AttrStr> getAttr_str_list() {
        return attr_str_list;
    }

    public void setAttr_str_list(List<AttrStr> attr_str_list) {
        this.attr_str_list = attr_str_list;
    }
}
