package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.CategoryModel;

import java.util.List;

/**
 * Created by chengang on 2017/7/10.
 */

public class BrandModel {
    private String brand_name;
    private String brand_id;
    private int uid;
    private String brand_image;
    private String brand_description;
    private int order_num;
    private String brand_cate;
    private List<CategoryModel> category;

    public String getBrand_cate() {
        return brand_cate;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBrand_image() {
        return brand_image;
    }

    public void setBrand_image(String brand_image) {
        this.brand_image = brand_image;
    }

    public String getBrand_description() {
        return brand_description;
    }

    public void setBrand_description(String brand_description) {
        this.brand_description = brand_description;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
    }
}
