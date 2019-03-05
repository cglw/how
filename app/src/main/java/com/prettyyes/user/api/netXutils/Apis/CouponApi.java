package com.prettyyes.user.api.netXutils.Apis;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.NetWorkCallback;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.Apis
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public interface CouponApi extends BaseApi {
    public abstract void CouponAdd(String uuid,String code, NetReqCallback netReqCallback);
    public abstract void CouponUse(String uuid,String code,float total_price, NetWorkCallback paramNetWorkCallback);
    public abstract void CouponList(String uuid,String coupon_type, NetReqCallback netReqCallback);
    public abstract void couponPromoCode(String uuid, NetWorkCallback paramNetWorkCallback);
    public abstract void couponAddShareCoupon(String uuid,String slogan, NetReqCallback netReqCallback);
    public abstract void couponExistShareCoupon(String uuid, NetReqCallback netReqCallback);

}
