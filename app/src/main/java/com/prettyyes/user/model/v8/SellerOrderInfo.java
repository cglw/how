package com.prettyyes.user.model.v8;

import java.util.List;

/**
 * Created by chengang on 2017/7/26.
 */

public class SellerOrderInfo {
    private String order_id;
    private String user_id;
    private String get_uname;
    private String order_number;
    private String total_price;
    private int ship_status;
    private int unit_num;
    private int order_status;
    private String pay_type;
    private String user_address;
    private String user_mobile;
    private String amount_price;
    private String user_remark;
    private String create_time;
    private String ship_company;
    private String ship_number;
    private String pay_time;
    private String ship_time;
    private String coupon_price;
    private int agree_cancel;
    private String express_price;
    private RefundEntity refund;

    public RefundEntity getRefund() {
        return refund;
    }

    private List<UnitListBean> unit_list;
    public static class UnitListBean {


        /**
         * order_unit_id : 0
         * price : string
         * main_img : string
         * num : 0
         * order_id : 0
         * module_id : 0
         * spu_name : string
         * spu_type : string
         */

        private int order_unit_id;
        private String price;
        private String main_img;
        private int num;
        private int order_id;
        private int module_id;
        private String spu_name;
        private String spu_type;

        public int getOrder_unit_id() {
            return order_unit_id;
        }

        public void setOrder_unit_id(int order_unit_id) {
            this.order_unit_id = order_unit_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMain_img() {
            return main_img;
        }

        public void setMain_img(String main_img) {
            this.main_img = main_img;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getModule_id() {
            return module_id;
        }

        public void setModule_id(int module_id) {
            this.module_id = module_id;
        }

        public String getSpu_name() {
            return spu_name;
        }

        public void setSpu_name(String spu_name) {
            this.spu_name = spu_name;
        }

        public String getSpu_type() {
            return spu_type;
        }

        public void setSpu_type(String spu_type) {
            this.spu_type = spu_type;
        }
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGet_uname() {
        return get_uname;
    }

    public void setGet_uname(String get_uname) {
        this.get_uname = get_uname;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
   public int getUnit_num() {
        return unit_num;
    }

    public void setUnit_num(int unit_num) {
        this.unit_num = unit_num;
    }


    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getAmount_price() {
        return amount_price;
    }

    public void setAmount_price(String amount_price) {
        this.amount_price = amount_price;
    }

    public String getUser_remark() {
        return user_remark;
    }

    public void setUser_remark(String user_remark) {
        this.user_remark = user_remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getShip_time() {
        return ship_time;
    }

    public void setShip_time(String ship_time) {
        this.ship_time = ship_time;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }


    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public List<UnitListBean> getUnit_list() {
        return unit_list;
    }

    public void setUnit_list(List<UnitListBean> unit_list) {
        this.unit_list = unit_list;
    }

    public int getShip_status() {
        return ship_status;
    }

    public void setShip_status(int ship_status) {
        this.ship_status = ship_status;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getAgree_cancel() {
        return agree_cancel;
    }

    public void setAgree_cancel(int agree_cancel) {
        this.agree_cancel = agree_cancel;
    }
}
