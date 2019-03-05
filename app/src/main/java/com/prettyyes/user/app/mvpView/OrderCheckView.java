package com.prettyyes.user.app.mvpView;

import com.prettyyes.user.model.coupon.CouponInfoEntity;
import com.prettyyes.user.model.v8.OrderCheckItemModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpView
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */

public interface OrderCheckView extends BaseView {

    String getUser_remark();

    int getPay_type();

    int getCoupon_id();

    int getAddress_id();

    int getBuy_now();

    void setName(String name);

    void setPhone(String phone);

    void setAddress(String addrss, int address_id);

    void setCoupon(List<CouponInfoEntity> datas,int select);

    void setSubprice(float subprice);

    void setPayPrice(float payprice);

    void setDiscount_price(float discount_price);


    void setLvData(List<OrderCheckItemModel> data);

    void setTvDefaultVisiable(int visiable);

    void setHaveCoupon(boolean have);

    void setOrderRemark(String json);


}
