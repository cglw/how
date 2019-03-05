package com.prettyyes.user.model.order;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.order
 * Author: SmileChen
 * Created on: 2016/10/28
 * Description: Nothing
 */
public class WechatPayEntity {
        private String device_info;
        private String nonce_str;
        private String prepay_id;
        private String sign;
        private String timestamp;

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

    @Override
    public String toString() {
        return "WechatPayEntity{" +
                "device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
