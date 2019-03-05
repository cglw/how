package com.prettyyes.user.model.coupon;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.coupon
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class CouponUse extends BaseModel {


    /**
     * total_price : 449
     * coupon_txt : 满100减50元
     * coupon_price : 50
     */

    private InfoEntity info;

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        private float total_price;
        private String coupon_txt;
        private String coupon_price;

        public void setTotal_price(float total_price) {
            this.total_price = total_price;
        }

        public void setCoupon_txt(String coupon_txt) {
            this.coupon_txt = coupon_txt;
        }

        public void setCoupon_price(String coupon_price) {
            this.coupon_price = coupon_price;
        }

        public float getTotal_price() {
            return total_price;
        }

        public String getCoupon_txt() {
            return coupon_txt;
        }

        public String getCoupon_price() {
            return coupon_price;
        }
    }
}
