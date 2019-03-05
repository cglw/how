package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.coupon.CouponInfoEntity;
import com.prettyyes.user.model.v8.CartInfoEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.order
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class OrderCheck extends BaseModel {


    /**
     * list : [{"seller_id":21885,"seller_name":"YUHUANhuan＊","seller_headimg":"",TYPE_SUIT:[{"seller_id":21885,"suit_id":9911,"suit_name":"泡沫","cover_img":"http://img.prettyyes.com/21885-3125-1462431288.jpeg","suit_status":1,"suit_unit":[{"suit_id":9911,"unit_id":32381,"unit_name":"仙美露肩连衣裙","price":"468.00","img":"http://img.prettyyes.com/21885-2545-1462431213.jpeg","num":1,"cart_id":203,"cart_status":2}]}],TYPE_TAOBAO:[{"cart_id":124,"cart_status":1,"seller_id":1085,"taobao_id":88,"title":"手表女学生正品韩国潮流时尚水钻女腕表石英表休闲时装表皮带手表","main_image":"http://img.alicdn.com/imgextra/i3/72113145/TB2fuGruXXXXXX3XXXXXXXXXXXX_!!72113145.jpg","price":"38.00","num":1,"taobao_status":1}],"seller_total_price":468,"seller_total_num":1}]
     * default_address : {"province_name":"内蒙古","city_name":"呼和浩特","area_name":null,"a_id":1592,"detail":"jjjjj","get_uname":"ttt","mobile":"yy","is_default":"1"}
     * total_price : 468
     * total_num : 1
     * coupon_list : [{"coupon_txt":"满199减50元","coupon_price":"50","code":"7BP83BVL","code_id":59066,"rule_code":"full_cut","coupon_name":"测试不要寻找答案优惠券1"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"还说","code":"6JHF948R","code_id":59060,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"打算发的发的","code":"C6C9G8FM","code_id":59058,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"打算发的发的","code":"MAOJD8KI","code_id":59057,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"千言万语总是无语","code":"E5K1V9EJ","code_id":59056,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"千言万语总是无语","code":"45I2V5AB","code_id":59055,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"爱恋不过是一场高烧，思念是紧跟着好不了的咳","code":"9BJARAAA","code_id":59054,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"移り行く時が涙に変わる","code":"8IQRI0J4","code_id":59050,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"移り行く時が涙に変わる","code":"QPK05URA","code_id":59049,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"show me you are the one","code":"HP2TDQB6","code_id":59046,"rule_code":"share"},{"coupon_txt":"满299.00减50.00元","coupon_price":"50.00","coupon_name":"移り行く時が涙に変わる","code":"OO7ADFJD","code_id":59044,"rule_code":"share"}]
     * chose_coupon : 59066
     * discount_price : 418
     */

    private InfoEntity info;

    public InfoEntity getInfo() {
        return info;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public static class InfoEntity implements Serializable {
        /**
         * province_name : 内蒙古
         * city_name : 呼和浩特
         * area_name : null
         * a_id : 1592
         * detail : jjjjj
         * get_uname : ttt
         * mobile : yy
         * is_default : 1
         */

        private DefaultAddressEntity default_address;
        private float total_price;
        private int total_num;
        private String chose_coupon;
        private float discount_price;
        /**
         * seller_id : 21885
         * seller_name : YUHUANhuan＊
         * seller_headimg :
         * suit : [{"seller_id":21885,"suit_id":9911,"suit_name":"泡沫","cover_img":"http://img.prettyyes.com/21885-3125-1462431288.jpeg","suit_status":1,"suit_unit":[{"suit_id":9911,"unit_id":32381,"unit_name":"仙美露肩连衣裙","price":"468.00","img":"http://img.prettyyes.com/21885-2545-1462431213.jpeg","num":1,"cart_id":203,"cart_status":2}]}]
         * taobao : [{"cart_id":124,"cart_status":1,"seller_id":1085,"taobao_id":88,"title":"手表女学生正品韩国潮流时尚水钻女腕表石英表休闲时装表皮带手表","main_image":"http://img.alicdn.com/imgextra/i3/72113145/TB2fuGruXXXXXX3XXXXXXXXXXXX_!!72113145.jpg","price":"38.00","num":1,"taobao_status":1}]
         * seller_total_price : 468
         * seller_total_num : 1
         */

        private List<ListEntity> list;
        /**
         * coupon_txt : 满199减50元
         * coupon_price : 50
         * code : 7BP83BVL
         * code_id : 59066
         * rule_code : full_cut
         * coupon_name : 测试不要寻找答案优惠券1
         */

        private List<CouponInfoEntity> coupon_list;

        public DefaultAddressEntity getDefault_address() {
            return default_address;
        }

        public void setDefault_address(DefaultAddressEntity default_address) {
            this.default_address = default_address;
        }

        public float getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public String getChose_coupon() {
            return chose_coupon;
        }

        public void setChose_coupon(String chose_coupon) {
            this.chose_coupon = chose_coupon;
        }

        public float getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(int discount_price) {
            this.discount_price = discount_price;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<CouponInfoEntity> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponInfoEntity> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public static class DefaultAddressEntity implements Serializable {
            private String province_name;
            private String city_name;
            private String area_name;
            private int a_id;
            private String detail;
            private String get_uname;
            private String mobile;
            private String is_default;

            public String getProvince_name() {
                return province_name;
            }

            public void setProvince_name(String province_name) {
                this.province_name = province_name;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public Object getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public int getA_id() {
                return a_id;
            }

            public void setA_id(int a_id) {
                this.a_id = a_id;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getGet_uname() {
                return get_uname;
            }

            public void setGet_uname(String get_uname) {
                this.get_uname = get_uname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }
        }

        public static class ListEntity implements Serializable {
            private int seller_id;
            private String seller_name;
            private String seller_headimg;
            private float seller_total_price;
            private int seller_total_num;
            private String seller_ace_txt;
            private int famous_type = 0;
            private String seller_express_price;
            private List<CartInfoEntity> cart_list;
            public String grade_title;

            public String getGrade_title() {
                return grade_title;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getSeller_name() {
                return seller_name;
            }

            public void setSeller_name(String seller_name) {
                this.seller_name = seller_name;
            }

            public String getSeller_headimg() {
                return seller_headimg;
            }

            public void setSeller_headimg(String seller_headimg) {
                this.seller_headimg = seller_headimg;
            }

            public float getSeller_total_price() {
                return seller_total_price;
            }

            public void setSeller_total_price(float seller_total_price) {
                this.seller_total_price = seller_total_price;
            }

            public int getSeller_total_num() {
                return seller_total_num;
            }

            public void setSeller_total_num(int seller_total_num) {
                this.seller_total_num = seller_total_num;
            }

            public String getSeller_ace_txt() {
                return seller_ace_txt;
            }

            public void setSeller_ace_txt(String seller_ace_txt) {
                this.seller_ace_txt = seller_ace_txt;
            }

            public int getFamous_type() {
                return famous_type;
            }

            public void setFamous_type(int famous_type) {
                this.famous_type = famous_type;
            }

            public String getSeller_express_price() {
                return seller_express_price;
            }

            public void setSeller_express_price(String seller_express_price) {
                this.seller_express_price = seller_express_price;
            }

            public List<CartInfoEntity> getCartList() {
                return cart_list;
            }

            public void setCartList(List<CartInfoEntity> cartList) {
                this.cart_list = cartList;
            }
        }

    }
}
