package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/18.
 */

public class AnswerDataBean extends BaseModel {
    /**
     * sku_id : 7
     * seller_id : 1085
     * price : 1000.00
     * sku_name : 我无所事事时lv
     * share_status : 1
     * brand_id : 3
     * brand_name : 嘻嘻嘻
     * brand_image : http://img.prettyyes.com/1085-4583-1451650313.jpeg
     * uid : 1085
     * category_name : xixixi
     * img_arr : ["https://si.geilicdn.com/vshop1147006992-1482837603102-3C930-s1.jpg?w=480&h=480","https://si.geilicdn.com/vshop1147006992-1482837603965-F58C3-s1.jpg?w=480&h=480","https://si.geilicdn.com/vshop1147006992-1482837604800-FB937-s1.jpg?w=480&h=480"]
     */
    private int totalwith;

    private String main_img;
    private String spu_id;
    private String module_id;
    private String seller_id;
    private String spu_price;
    private String spu_name;
    private String spu_type;
    private int share_status = 0;
    private String brand_id;
    private String brand_name;
    private String brand_image;
    private String uid;
    private String category_name;
    private List<String> img_arr;
    private String buy;
    private String spu_desc;

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getMoudle_id() {
        return module_id;
    }

    public void setMoudle_id(String moudle_id) {
        this.module_id = moudle_id;
    }

    public String getDesc() {
        return spu_desc;
    }

    public void setDesc(String desc) {
        this.spu_desc = desc;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public int getTotalwith() {
        return totalwith;
    }

    public void setTotalwith(int totalwith) {
        this.totalwith = totalwith;
    }


    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public int getShare_status() {
        return share_status;
    }

    public void setShare_status(int share_status) {
        this.share_status = share_status;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPrice() {
        return spu_price;
    }

    public void setPrice(String price) {
        this.spu_price = price;
    }

    public String getSku_name() {
        return spu_name;
    }

    public void setSku_name(String sku_name) {
        this.spu_name = sku_name;
    }


    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_image() {
        return brand_image;
    }

    public void setBrand_image(String brand_image) {
        this.brand_image = brand_image;
    }


    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<String> getImg_arr() {
        return img_arr;
    }

    public void setImg_arr(List<String> img_arr) {
        this.img_arr = img_arr;
    }

    public String getSku_type() {
        return spu_type;
    }

    public void setSku_type(String spu_type) {
        this.spu_type = spu_type;
    }
}
