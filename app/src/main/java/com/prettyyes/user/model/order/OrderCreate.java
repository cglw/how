package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.order
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class OrderCreate extends BaseModel{
    /**
     * order_number : 161027315473461
     * amount_price : 716
     * pay_type : 1
     * wechat_pay : {"device_info":"WEB","nonce_str":"xl2CJq91wjZui47u","prepay_id":"wx20161028143829078f34d1410263440673","sign":"D5DC161231EB5642B799339C8383C304","timestamp":"1477636709"}
     * alipay : {"str":"_input_charset=\"utf-8\"&body=\"How订单161027315473461\"&notify_url=\"http://laravel.pretty.local/app/pay/alipayNotify\"&out_trade_no=\"161027315473461\"&partner=\"2088421550730483\"&payment_type=\"1\"&seller_id=\"dev@prettyyes.com\"&service=\"mobile.securitypay.pay\"&subject=\"How订单161027315473461\"&total_fee=\"716\"&sign=\"VYLuf3IyzXgbO4wRwlv2pyo%2FMUSKxmrTWCZnDRxmpKP1Xtp7KGvzbF3iEHtrxn1Dh%2BuuVLIizTZEwY%2FbGoXMMTEkyXmOTge%2BXJAu4ecark6Uumzr%2BZSpCI%2B%2FnSPVaNHRZe9x%2BHa3HFrqSNhHJFSNmkn3zPd2nh2g2TeY1BYOOVw%3D\"&sign_type=\"RSA\""}
     */

    private InfoEntity info;

    public InfoEntity getInfo() {
        return info;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public static class InfoEntity {
        private String order_number;
        private float amount_price;
        private String pay_type;
        private String reward_number;

        public String getReward_number() {
            return reward_number;
        }

        public void setReward_number(String reward_number) {
            this.reward_number = reward_number;
        }

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

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public float getAmount_price() {
            return amount_price;
        }

        public void setAmount_price(float amount_price) {
            this.amount_price = amount_price;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

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


//    /**
//     * order_id : 2539
//     * order_number : 160726116732606
//     * amount_price : 1020
//     * pay_type : 1
//     */
//
//    private InfoEntity info;
//
//    public void setInfo(InfoEntity info) {
//        this.info = info;
//    }
//
//    public InfoEntity getInfo() {
//        return info;
//    }
//
//    public static class InfoEntity {
//        private int order_id;
//        private String order_number;
//        private float amount_price;
//        private String pay_type;
//        private String nonce_str;
//        private String prepay_id;
//        private String sign;
//        private String timestamp;
//
//
//        public void setOrder_id(int order_id) {
//            this.order_id = order_id;
//        }
//
//        public void setOrder_number(String order_number) {
//            this.order_number = order_number;
//        }
//
//        public void setAmount_price(float amount_price) {
//            this.amount_price = amount_price;
//        }
//
//        public void setPay_type(String pay_type) {
//            this.pay_type = pay_type;
//        }
//
//        public int getOrder_id() {
//            return order_id;
//        }
//
//        public String getOrder_number() {
//            return order_number;
//        }
//
//        public float getAmount_price() {
//            return amount_price;
//        }
//
//        public String getPay_type() {
//            return pay_type;
//        }
//
//        public String getNonce_str() {
//            return nonce_str;
//        }
//
//        public void setNonce_str(String nonce_str) {
//            this.nonce_str = nonce_str;
//        }
//
//        public String getPrepay_id() {
//            return prepay_id;
//        }
//
//        public void setPrepay_id(String prepay_id) {
//            this.prepay_id = prepay_id;
//        }
//
//        public String getSign() {
//            return sign;
//        }
//
//        public void setSign(String sign) {
//            this.sign = sign;
//        }
//
//        public String getTimestamp() {
//            return timestamp;
//        }
//
//        public void setTimestamp(String timestamp) {
//            this.timestamp = timestamp;
//        }
//    }


    @Override
    public String toString() {
        return "OrderCreate{" +
                "info=" + info +
                '}';
    }
}
