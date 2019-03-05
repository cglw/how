package com.prettyyes.user.core.event;

import com.prettyyes.user.model.applocal.AttrName;

import java.util.List;

/**
 * Created by chengang on 2017/6/30.
 */

public class AttrSelectEvent {
    private String attr_json;
    private List<AttrName>attrNames;


    public List<AttrName> getAttrNames() {
        return attrNames;
    }

    public String getAttr_json() {
        return attr_json;
    }

    public AttrSelectEvent(String attr_json, List<AttrName> attrNames) {
        this.attr_json = attr_json;
        this.attrNames = attrNames;
    }
}
