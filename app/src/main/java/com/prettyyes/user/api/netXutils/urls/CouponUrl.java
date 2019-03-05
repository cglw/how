package com.prettyyes.user.api.netXutils.urls;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.urls
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: 优惠券Url
 */
public class CouponUrl extends BaseUrl{
    //添加分享优惠券
    public static final String Url_couponAddshareCoupon= url+"/app/coupon/addShareCoupon";
    //是否已经添加了分享优惠
    public static final String Url_couponExistShareCoupon= url+"/app/coupon/existShareCoupon";
    //分享优惠券
    public static final String Url_couponPromoCode= url+"/app/coupon/promoCode";
    //添加优惠券
    public static final String Url_couponAdd= url+"/app/coupon/add";
    //使用优惠券
    public static final String Url_couponUse= url+"/app/coupon/use";
    //优惠券列表
    public static final String Url_couponList= url+"/app/coupon/list";
}
