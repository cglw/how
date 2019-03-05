package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.CouponApi;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.urls.CouponUrl;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.coupon.AddShareCoupon;
import com.prettyyes.user.model.coupon.CouponInfoEntity;
import com.prettyyes.user.model.coupon.CouponList;
import com.prettyyes.user.model.coupon.CouponUse;
import com.prettyyes.user.model.coupon.ExistShareCoupon;
import com.prettyyes.user.model.coupon.PromoCodeUrl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.ApiImpls
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class CouponApiImpl implements CouponApi {
    /**
     * 添加优惠券 根据res判断
     *
     * @param uuid
     * @param code
     * @param netReqCallback
     */
    @Override
    public void CouponAdd(String uuid, String code, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("code", code);
        Type localType = new TypeToken<ApiResContent<ListCommon<List<CouponInfoEntity>>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(CouponUrl.Url_couponAdd, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"CouponAdd");
        }
    }

    /**
     * 使用优惠券
     *
     * @param uuid
     * @param code
     * @param total_price
     * @param paramNetWorkCallback
     */
    @Override
    public void CouponUse(String uuid, String code, float total_price, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("code", code);
        localHashMap.put("total_price", total_price);
        Type localType = new TypeToken<ApiResContent<CouponUse>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(CouponUrl.Url_couponUse, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 优惠券列表
     * @param uuid
     * @param coupon_type (选填) 优惠券类型 0 所有优惠券 1 下单可用优惠券 2 打赏优惠券
     * @param netReqCallback
     */
    @Override
    public void CouponList(String uuid,String coupon_type, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("coupon_type", coupon_type);
        Type localType = new TypeToken<ApiResContent<CouponList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(CouponUrl.Url_couponList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"CouponList");
        }
    }

    /**
     * 分享优惠券
     *
     * @param uuid
     * @param paramNetWorkCallback
     */
    @Override
    public void couponPromoCode(String uuid, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<PromoCodeUrl>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(CouponUrl.Url_couponPromoCode, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 添加分享优惠券
     *
     * @param uuid
     * @param slogan
     * @param netReqCallback
     */
    @Override
    public void couponAddShareCoupon(String uuid, String slogan, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("slogan", slogan);
        Type localType = new TypeToken<ApiResContent<AddShareCoupon>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(CouponUrl.Url_couponAddshareCoupon, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"couponAddShareCoupon");
        }
    }

    /**
     * 是否已经添加了分享优惠券
     *
     * @param uuid
     * @param netReqCallback
     */

    @Override
    public void couponExistShareCoupon(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<ExistShareCoupon>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(CouponUrl.Url_couponExistShareCoupon, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"couponExistShareCoupon");
        }
    }
}
