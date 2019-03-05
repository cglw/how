package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/4.
 */

public class OrderCheckItemModel {

    public final static String TYPE_TOP = "top";
    public final static String TYPE_BOTTOM = "bottom";
    public final static String TYPE_CENTER = "center";
    private String item_type;
    //top
    private SellerInfoEntity sellerInfoEntity;


    //center
    private String module_id;
    private String spu_type;
    private String spu_price;
    private String main_img;
    private String spu_name;
    private int num;
    private String cart_id;
    private String cart_status;
    private String attr_value_text;


    //bottom
    private String totalprice;
    private String remark = "";
    private String totalnumber;
    private String express_price;
    private String seller_id;

    public String getAttr_value_text() {
        return attr_value_text;
    }

    public void setAttr_value_text(String attr_value_text) {
        this.attr_value_text = attr_value_text;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public SellerInfoEntity getSellerInfoEntity() {
        return sellerInfoEntity;
    }

    public void setSellerInfoEntity(SellerInfoEntity sellerInfoEntity) {
        this.sellerInfoEntity = sellerInfoEntity;
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

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_status() {
        return cart_status;
    }

    public void setCart_status(String cart_status) {
        this.cart_status = cart_status;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(String totalnumber) {
        this.totalnumber = totalnumber;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getSpu_price() {
        return spu_price;
    }

    public void setSpu_price(String spu_price) {
        this.spu_price = spu_price;
    }
}
