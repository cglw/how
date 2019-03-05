package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.coupon.CouponInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalOrderRes extends BaseRes{
    private List<CouponInfoEntity>coupon_list;
    private String order_number;

    public List<CouponInfoEntity> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<CouponInfoEntity> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
}
