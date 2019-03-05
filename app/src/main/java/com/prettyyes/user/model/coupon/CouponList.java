package com.prettyyes.user.model.coupon;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.coupon
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class CouponList extends BaseModel{


    /**
     * code_id : 57799
     * code : 7VTJ2G3U
     * name : 男装二次购买满100-50
     * content : 男装二次购买满100-50
     * end_time : 2019-03-01
     * coupon_price : 50.00
     * gift_list : []
     * coupon_txt : 满100减50元
     */

    private List<CouponInfoEntity> list;

    public void setList(List<CouponInfoEntity> list) {
        this.list = list;
    }

    public List<CouponInfoEntity> getList() {
        return list;
    }


}
