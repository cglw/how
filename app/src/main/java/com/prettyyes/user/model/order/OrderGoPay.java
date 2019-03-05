package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class OrderGoPay extends BaseModel {


    /**
     * wechat_pay : {"device_info":"WEB","nonce_str":"xl2CJq91wjZui47u","prepay_id":"wx20161028143829078f34d1410263440673","sign":"D5DC161231EB5642B799339C8383C304","timestamp":"1477636709"}
     * alipay : {"str":"_input_charset=\"utf-8\"&body=\"How订单161027315473461\"&notify_url=\"http://laravel.pretty.local/app/pay/alipayNotify\"&out_trade_no=\"161027315473461\"&partner=\"2088421550730483\"&payment_type=\"1\"&seller_id=\"dev@prettyyes.com\"&service=\"mobile.securitypay.pay\"&subject=\"How订单161027315473461\"&total_fee=\"716\"&sign=\"VYLuf3IyzXgbO4wRwlv2pyo%2FMUSKxmrTWCZnDRxmpKP1Xtp7KGvzbF3iEHtrxn1Dh%2BuuVLIizTZEwY%2FbGoXMMTEkyXmOTge%2BXJAu4ecark6Uumzr%2BZSpCI%2B%2FnSPVaNHRZe9x%2BHa3HFrqSNhHJFSNmkn3zPd2nh2g2TeY1BYOOVw%3D\"&sign_type=\"RSA\""}
     */


    /**
     * device_info : WEB
     * nonce_str : xl2CJq91wjZui47u
     * prepay_id : wx20161028143829078f34d1410263440673
     * sign : D5DC161231EB5642B799339C8383C304
     * timestamp : 1477636709
     */


    private WechatPayEntity wechat_pay;
    /**
     * str : _input_charset="utf-8"&body="How订单161027315473461"&notify_url="http://laravel.pretty.local/app/pay/alipayNotify"&out_trade_no="161027315473461"&partner="2088421550730483"&payment_type="1"&seller_id="dev@prettyyes.com"&service="mobile.securitypay.pay"&subject="How订单161027315473461"&total_fee="716"&sign="VYLuf3IyzXgbO4wRwlv2pyo%2FMUSKxmrTWCZnDRxmpKP1Xtp7KGvzbF3iEHtrxn1Dh%2BuuVLIizTZEwY%2FbGoXMMTEkyXmOTge%2BXJAu4ecark6Uumzr%2BZSpCI%2B%2FnSPVaNHRZe9x%2BHa3HFrqSNhHJFSNmkn3zPd2nh2g2TeY1BYOOVw%3D"&sign_type="RSA"
     */

    private AlipayEntity alipay;

    public WechatPayEntity getWechat_pay() {
        return wechat_pay;
    }

    public void setWechat_pay(WechatPayEntity wechat_pay) {
        this.wechat_pay = wechat_pay;
    }

    public AlipayEntity getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayEntity alipay) {
        this.alipay = alipay;
    }


}
