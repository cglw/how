package com.prettyyes.user.core;

import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.applocal.AttrVaule;
import com.prettyyes.user.model.v8.AttrListBean;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by chengang on 2017/5/19.
 */

public class DataChange {





//
//    public static ArrayList<AttrName> getAttrNames(SpuInfoEntity spuInfoEntity) {
//
//        List<AttrListBean> attr_list = spuInfoEntity.getSku_list().getAttr_list();
//        return attr_list_to_attrnames(attr_list);
//
//    }

//    public static UnitDetailEntity SpuInfoToUnit(SpuInfoEntity spuInfoEntity)
//    {
//        UnitDetailEntity unitDetailBean=new UnitDetailEntity();
//        unitDetailBean.setSpu_name(spuInfoEntity.getSpu_name());
//        unitDetailBean.setSpu_price(spuInfoEntity.getSpu_price());
//        unitDetailBean.setExpress_price(spuInfoEntity.getExpress_price());
//        unitDetailBean.setMain_img(spuInfoEntity.getMain_img());
//        unitDetailBean.setAttr_json(spuInfoEntity.getSku_list().getAttr_json());
//        unitDetailBean.setAttr_list(spuInfoEntity.getSku_list().getAttr_list());
//        unitDetailBean.setAttr_str_list(spuInfoEntity.getSku_list().getAttr_str_list());
//        return unitDetailBean;
//
//
//    }

    private static ArrayList<AttrName> attr_list_to_attrnames(List<AttrListBean> attr_list) {
        ArrayList<AttrName> attrnames = new ArrayList<>();

        if(attr_list==null||attr_list.size()<=0)
            return attrnames;

        for (int i = 0; i < attr_list.size(); i++) {
            AttrName attrname = new AttrName();
            attrname.setName(attr_list.get(i).getAttr_name());
            attrname.setAttr_id(attr_list.get(i).getAttr_id());
            LinkedHashMap<String, AttrVaule> stringAttrVauleHashMap = attr_list.get(i).getValues();
            List<AttrVaule> attrVaules = new ArrayList<>();
            for (String key : stringAttrVauleHashMap.keySet()) {
                AttrVaule attrVaule = stringAttrVauleHashMap.get(key);
                attrVaules.add(attrVaule);
            }
            attrname.setValues(attrVaules);
            attrnames.add(attrname);
        }
        return attrnames;
    }

    public static ArrayList<AttrName> getAttrNames(SpuInfoEntity unitDetailBean) {
        List<AttrListBean> attr_list = unitDetailBean.getAttr_list();

        return attr_list_to_attrnames(attr_list);
    }
}
