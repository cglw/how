package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.coupon.CouponList;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/28.
 */

public class CouponLIstRequest extends BaseRequest<CouponList> {

    private String coupon_type;

    public CouponLIstRequest setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("coupon_type", coupon_type);
        return super.setParams();
    }

    @Override
    public String setExtraUrl() {
        return "/app/coupon/list";
    }
}
