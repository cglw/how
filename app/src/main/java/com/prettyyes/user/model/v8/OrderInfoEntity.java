package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.order.OrderInfo;

/**
 * Created by chengang on 2017/10/10.
 */

public class OrderInfoEntity {
    private String order_number;
    private String order_number_parent;
    private String order_status;
    private String ship_status;
    private int user_id;
    private int seller_id;
    private String get_uname;
    private String user_remark;
    private String user_mobile;
    private String user_address;
    private String pay_type;
    private String total_price;
    private String amount_price;
    private String coupon_price;
    private String active_price;
    private int coupon_code_id;
    private String coupon_str;
    private String coupon_name;
    private String coupon_txt;
    private String ship_company;
    private String ship_number;
    private int unit_num;
    private String create_time;
    private String ship_time;
    private String agree_cancel;
    private String seller_name;
    private String seller_headimg;
    private String seller_ace_txt;
    private int seller_famous=0;
    private OrderInfo.InfoEntity.UnitListEntity unit_list;
    public String grade_title;
    private RefundEntity refund;

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_number_parent() {
        return order_number_parent;
    }

    public void setOrder_number_parent(String order_number_parent) {
        this.order_number_parent = order_number_parent;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getShip_status() {
        return ship_status;
    }

    public void setShip_status(String ship_status) {
        this.ship_status = ship_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getGet_uname() {
        return get_uname;
    }

    public void setGet_uname(String get_uname) {
        this.get_uname = get_uname;
    }

    public String getUser_remark() {
        return user_remark;
    }

    public void setUser_remark(String user_remark) {
        this.user_remark = user_remark;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getAmount_price() {
        return amount_price;
    }

    public void setAmount_price(String amount_price) {
        this.amount_price = amount_price;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getActive_price() {
        return active_price;
    }

    public void setActive_price(String active_price) {
        this.active_price = active_price;
    }

    public int getCoupon_code_id() {
        return coupon_code_id;
    }

    public void setCoupon_code_id(int coupon_code_id) {
        this.coupon_code_id = coupon_code_id;
    }

    public String getCoupon_str() {
        return coupon_str;
    }

    public void setCoupon_str(String coupon_str) {
        this.coupon_str = coupon_str;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_txt() {
        return coupon_txt;
    }

    public void setCoupon_txt(String coupon_txt) {
        this.coupon_txt = coupon_txt;
    }

    public String getShip_company() {
        return ship_company;
    }

    public void setShip_company(String ship_company) {
        this.ship_company = ship_company;
    }

    public String getShip_number() {
        return ship_number;
    }

    public void setShip_number(String ship_number) {
        this.ship_number = ship_number;
    }

    public int getUnit_num() {
        return unit_num;
    }

    public void setUnit_num(int unit_num) {
        this.unit_num = unit_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getShip_time() {
        return ship_time;
    }

    public void setShip_time(String ship_time) {
        this.ship_time = ship_time;
    }

    public String getAgree_cancel() {
        return agree_cancel;
    }

    public void setAgree_cancel(String agree_cancel) {
        this.agree_cancel = agree_cancel;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_headimg() {
        return seller_headimg;
    }

    public void setSeller_headimg(String seller_headimg) {
        this.seller_headimg = seller_headimg;
    }

    public String getSeller_ace_txt() {
        return seller_ace_txt;
    }

    public void setSeller_ace_txt(String seller_ace_txt) {
        this.seller_ace_txt = seller_ace_txt;
    }

    public int getSeller_famous() {
        return seller_famous;
    }

    public void setSeller_famous(int seller_famous) {
        this.seller_famous = seller_famous;
    }

    public OrderInfo.InfoEntity.UnitListEntity getUnit_list() {
        return unit_list;
    }

    public void setUnit_list(OrderInfo.InfoEntity.UnitListEntity unit_list) {
        this.unit_list = unit_list;
    }

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public RefundEntity getRefund() {
        return refund;
    }

    public void setRefund(RefundEntity refund) {
        this.refund = refund;
    }
}
