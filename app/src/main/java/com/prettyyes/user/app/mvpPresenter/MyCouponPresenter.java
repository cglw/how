package com.prettyyes.user.app.mvpPresenter;

import android.text.TextUtils;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.CouponApiImpl;
import com.prettyyes.user.app.mvpView.MyCouponView;
import com.prettyyes.user.model.common.ListCommon;
import com.prettyyes.user.model.coupon.CouponInfoEntity;
import com.prettyyes.user.model.coupon.CouponList;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.mvpPresenter
 * Author: SmileChen
 * Created on: 2016/10/31
 * Description: Nothing
 */
public class MyCouponPresenter {
    private MyCouponView myCouponView;
    private CouponApiImpl couponApi;

    public MyCouponPresenter(MyCouponView myCouponView) {
        this.myCouponView = myCouponView;
        couponApi = new CouponApiImpl();
    }

    public void getCouponListData() {
        couponApi.CouponList(myCouponView.getThis().getUUId(), "0", new NetReqCallback<CouponList>() {
            @Override
            public void apiRequestFail(String message,String method) {

            }

            @Override
            public void apiRequestSuccess(CouponList couponList,String method) {

            }

        });
    }

    public void shareTofriend() {
//        myCouponView.getThis().nextActivity(CouponShareActivity.class);
        //  WebviewActivity.goWebActivity(myCouponView.getThis(), AppConfig.getUrl()+"/share_friends");
    }

    public void addCoupoon() {
        if (TextUtils.isEmpty(myCouponView.getCouponTxt())) {
            myCouponView.showFailedError("请输入优惠码");
            return;
        }
        couponApi.CouponAdd(myCouponView.getThis().getUUId(), myCouponView.getCouponTxt(), new NetReqCallback<ListCommon<List<CouponInfoEntity>>>() {
            @Override
            public void apiRequestFail(String message,String method) {
                myCouponView.showFailedError(message);
            }

            @Override
            public void apiRequestSuccess(ListCommon<List<CouponInfoEntity>> listListCommon, String method) {
                getCouponListData();
            }
        });

    }
}
