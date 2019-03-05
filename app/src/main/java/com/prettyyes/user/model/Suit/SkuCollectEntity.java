package com.prettyyes.user.model.Suit;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.Suit
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SkuCollectEntity extends BaseModel {


    /**
     * spu_id : 29490
     * spu_name : 123
     * spu_price : 23.00
     * module_id : 12450
     * spu_type : suit
     * main_img : https://image.prettyyes.com/1085-02126-1500544483.jpeg
     */

    private String spu_id;
    private String spu_name;
    private String spu_price;
    private String module_id;
    private String spu_type;
    private String main_img;

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_price() {
        return spu_price;
    }

    public void setSpu_price(String spu_price) {
        this.spu_price = spu_price;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getSpu_type() {
        return spu_type;
    }

    public void setSpu_type(String spu_type) {
        this.spu_type = spu_type;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }
}