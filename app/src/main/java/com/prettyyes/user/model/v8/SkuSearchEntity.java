package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/6/28.
 */

public class SkuSearchEntity extends BaseModel{


    /**
     * item_id : 0
     * sku_name : string
     * price : 0
     * main_image : string
     * small_image : [""]
     * sku_status : 0
     * shop_name : string
     * item_url : string
     * express_price : string
     * create_time : string
     * platform : string
     * sku_desc : string
     */

    private String item_id;
    private String sku_name;
    private String price;
    private String main_image;
    private int sku_status;
    private String shop_name;
    private String item_url;
    private String express_price;
    private String create_time;
    private String platform;
    private String sku_desc;
    private List<String> small_image;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public int getSku_status() {
        return sku_status;
    }

    public void setSku_status(int sku_status) {
        this.sku_status = sku_status;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSku_desc() {
        return sku_desc;
    }

    public void setSku_desc(String sku_desc) {
        this.sku_desc = sku_desc;
    }

    public List<String> getSmall_image() {
        return small_image;
    }

    public void setSmall_image(List<String> small_image) {
        this.small_image = small_image;
    }
}
