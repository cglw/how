package com.prettyyes.user.model.v8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/6/28.
 */

public class SpuInfoEntity extends SpuCommonInfoEntity {


    /**
     * spu_id : 0
     * module_id : 0
     * spu_type : string
     * spu_name : string
     * main_img : string
     * small_img : string
     * spu_price : string
     * spu_desc : string
     * share_status : 0
     * status : 0
     * spu_status : 0
     * rubbish : string
     * bright_point : string
     * express_price : string
     * unit_detail : [{"unit_id":0,"module_id":"string","spu_name":"string","spu_type":"string","spu_desc":"string","main_img":"string","order_num":0,"spu_price":"string","attr_json":{"sku_id":0,"sku_price":"string","sku_store":"string","attr":[{"sku_attr_id":"string","sku_id":"string","attr_id":"string","parent_attr_id":"string","attr_value":"string","attr_value_id":"string"}]},"attr_str_list":{"attr_id":0,"attr_name":"string","values":[{"attr_value_id":0,"attr_value":"string"}]},"attr_list":{"attr_id":0,"attr_name":"string","values":[{"attr_value_id":0,"attr_value":"string"}]}}]
     * sku_list : {"attr_json":{"sku_id":0,"sku_price":"string","sku_store":"string","attr":[{"sku_attr_id":"string","sku_id":"string","attr_id":"string","parent_attr_id":"string","attr_value":"string","attr_value_id":"string"}]},"attr_str_list":{"attr_id":0,"attr_name":"string","values":[{"attr_value_id":0,"attr_value":"string"}]},"attr_list":{"attr_id":0,"attr_name":"string","values":[{"attr_value_id":0,"attr_value":"string"}]}}
     * seller_info : [{"nickname":"string","headimg":"string","ace_txt":"string","famous_type":0,"seller_id":0}]
     */


    private List<SpuInfoEntity> unit_detail;
    private List<SpuInfoEntity> unit_list;
    private String uid;
    private String seller_id;
    private List<String> img_arr;
    private String buy;


    public List<String> getShowImg() {
        List<String> imgs = new ArrayList<>();
//        if (main_img != null)
//            imgs.add(main_img);
        if (img_arr != null)
            imgs.addAll(img_arr);
        if (imgs.size() == 0)
            imgs.add(getMain_img());

        return imgs;
    }

    private static final String TAG = "SpuInfoEntity";

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public List<String> getImg_arr() {
        return img_arr;
    }

    public void setImg_arr(List<String> img_arr) {
        this.img_arr = img_arr;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public List<SpuInfoEntity> getUnit_list() {
        return unit_list;
    }

    public void setUnit_list(List<SpuInfoEntity> unit_list) {
        this.unit_list = unit_list;
    }

    public List<SpuInfoEntity> getUnit_detail() {
        return unit_detail;
    }

    public void setUnit_detail(List<SpuInfoEntity> unit_detail) {
        this.unit_detail = unit_detail;
    }

    public List<SpuInfoEntity> getUnits() {
        if (unit_list != null && unit_list.size() > 0) {
            return unit_list;
        } else if (unit_detail != null && unit_detail.size() > 0) {
            return unit_detail;
        } else
            return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SpuInfoEntity{" +
                "unit_detail=" + unit_detail +
                ", unit_list=" + unit_list +
                ", uid='" + uid + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", img_arr=" + img_arr +
                ", buy='" + buy + '\'' +
                '}';
    }
}
