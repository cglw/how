package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class OrderList extends BaseModel{

    /**
     * total_price : 7457.03
     * amount_price : 7357.03
     * unit_num : 34
     * order_number_parent : 161102878923709
     * order_status : 1
     * pay_type : 1
     * order_child : [{"order_number":"161102201663710","order_number_parent":"161102878923709","order_status":"1","ship_status":"0","seller_id":15107,"pay_type":"1","total_price":"297.02","amount_price":"293.04","coupon_price":"3.98","active_price":"0.00","unit_num":5,"create_time":"2016-11-02 17:07:24","agree_cancel":"0","unit_list":[{"unit_id":36513,"unit_name":"荷叶边蝴蝶结绑带无袖上衣","price":"99.00","unit_img":"http://img.prettyyes.com/15107-6710-1464350435.jpeg","unit_nums":3,"unit_type":TYPE_SUIT},{"unit_id":36514,"unit_name":"雪纺高腰7分阔腿裤","price":"0.01","unit_img":"http://img.prettyyes.com/15107-3241-1464350466.jpeg","unit_nums":2,"unit_type":TYPE_SUIT}]},{"order_number":"161102021043711","order_number_parent":"161102878923709","order_status":"1","ship_status":"0","seller_id":21885,"pay_type":"1","total_price":"0.01","amount_price":"0.01","coupon_price":"0.00","active_price":"0.00","unit_num":1,"create_time":"2016-11-02 17:07:24","agree_cancel":"0","unit_list":[{"unit_id":32381,"unit_name":"仙美露肩连衣裙","price":"0.01","unit_img":"http://img.prettyyes.com/21885-2545-1462431213.jpeg","unit_nums":1,"unit_type":TYPE_SUIT}]},{"order_number":"161102116923712","order_number_parent":"161102878923709","order_status":"1","ship_status":"0","seller_id":13609,"pay_type":"1","total_price":"2384.00","amount_price":"2352.03","coupon_price":"31.97","active_price":"0.00","unit_num":8,"create_time":"2016-11-02 17:07:24","agree_cancel":"0","unit_list":[{"unit_id":30734,"unit_name":"牛仔纽扣排扣 伞裙设计复古风","price":"298.00","unit_img":"http://img.prettyyes.com/13609-6978-1461983168.jpeg","unit_nums":8,"unit_type":TYPE_SUIT}]},{"order_number":"161102422223713","order_number_parent":"161102878923709","order_status":"1","ship_status":"0","seller_id":6105,"pay_type":"1","total_price":"4352.00","amount_price":"4293.64","coupon_price":"58.36","active_price":"0.00","unit_num":18,"create_time":"2016-11-02 17:07:24","agree_cancel":"0","unit_list":[{"unit_id":15573,"unit_name":"blue","price":"156.00","unit_img":"http://img.prettyyes.com/6105-5777-1457956929.jpeg","unit_nums":5,"unit_type":"suit"},{"unit_id":15574,"unit_name":"Jean","price":"236.00","unit_img":"http://img.prettyyes.com/6105-7236-1457956974.jpeg","unit_nums":5,"unit_type":"suit"},{"unit_id":15575,"unit_name":"black","price":"299.00","unit_img":"http://img.prettyyes.com/6105-6357-1457957245.jpeg","unit_nums":8,"unit_type":"suit"}]},{"order_number":"161102604023714","order_number_parent":"161102878923709","order_status":"1","ship_status":"0","seller_id":1135,"pay_type":"1","total_price":"424.00","amount_price":"418.31","coupon_price":"5.69","active_price":"0.00","unit_num":2,"create_time":"2016-11-02 17:07:24","agree_cancel":"0","unit_list":[{"unit_id":3282,"unit_name":"包","price":"126.00","unit_img":"http://img.prettyyes.com/1135-2304-1445841878.jpeg","unit_nums":1,"unit_type":"suit"},{"unit_id":3283,"unit_name":"马丁靴","price":"298.00","unit_img":"http://img.prettyyes.com/1135-1322-1445842017.jpeg","unit_nums":1,"unit_type":"suit"}]}]
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private String total_price;
        private String amount_price;
        private String unit_num;
        private String order_number_parent;
        private String order_status;
        private String pay_type;
        /**
         * order_number : 161102201663710
         * order_number_parent : 161102878923709
         * order_status : 1
         * ship_status : 0
         * seller_id : 15107
         * pay_type : 1
         * total_price : 297.02
         * amount_price : 293.04
         * coupon_price : 3.98
         * active_price : 0.00
         * unit_num : 5
         * create_time : 2016-11-02 17:07:24
         * agree_cancel : 0
         * unit_list : [{"unit_id":36513,"unit_name":"荷叶边蝴蝶结绑带无袖上衣","price":"99.00","unit_img":"http://img.prettyyes.com/15107-6710-1464350435.jpeg","unit_nums":3,"unit_type":"suit"},{"unit_id":36514,"unit_name":"雪纺高腰7分阔腿裤","price":"0.01","unit_img":"http://img.prettyyes.com/15107-3241-1464350466.jpeg","unit_nums":2,"unit_type":"suit"}]
         */

        private List<OrderChildEntity> order_child;

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getAmount_price() {
            return amount_price;
        }

        public void setAmount_price(String amount_price) {
            this.amount_price = amount_price;
        }

        public String getUnit_num() {
            return unit_num;
        }

        public void setUnit_num(String unit_num) {
            this.unit_num = unit_num;
        }

        public String getOrder_number_parent() {
            return order_number_parent;
        }

        public void setOrder_number_parent(String order_number_parent) {
            this.order_number_parent = order_number_parent;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public List<OrderChildEntity> getOrder_child() {
            return order_child;
        }

        public void setOrder_child(List<OrderChildEntity> order_child) {
            this.order_child = order_child;
        }

        public static class OrderChildEntity {
            private String order_number;
            private String order_number_parent;
            private String order_status;
            private String ship_status;
            private String is_comment;
            private int seller_id;
            private String pay_type;
            private String total_price;
            private String amount_price;
            private String coupon_price;
            private String active_price;
            private int unit_num;
            private String create_time;
            private String agree_cancel;

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            /**
             * unit_id : 36513
             * unit_name : 荷叶边蝴蝶结绑带无袖上衣
             * price : 99.00
             * unit_img : http://img.prettyyes.com/15107-6710-1464350435.jpeg
             * unit_nums : 3
             * unit_type : suit
             */

            private List<UnitListEntity> unit_list;

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getOrder_number_parent() {
                return order_number_parent;
            }

            public void setOrder_number_parent(String order_number_parent) {
                this.order_number_parent = order_number_parent;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getShip_status() {
                return ship_status;
            }

            public void setShip_status(String ship_status) {
                this.ship_status = ship_status;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getAmount_price() {
                return amount_price;
            }

            public void setAmount_price(String amount_price) {
                this.amount_price = amount_price;
            }

            public String getCoupon_price() {
                return coupon_price;
            }

            public void setCoupon_price(String coupon_price) {
                this.coupon_price = coupon_price;
            }

            public String getActive_price() {
                return active_price;
            }

            public void setActive_price(String active_price) {
                this.active_price = active_price;
            }

            public int getUnit_num() {
                return unit_num;
            }

            public void setUnit_num(int unit_num) {
                this.unit_num = unit_num;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getAgree_cancel() {
                return agree_cancel;
            }

            public void setAgree_cancel(String agree_cancel) {
                this.agree_cancel = agree_cancel;
            }

            public List<UnitListEntity> getUnit_list() {
                return unit_list;
            }

            public void setUnit_list(List<UnitListEntity> unit_list) {
                this.unit_list = unit_list;
            }

            public static class UnitListEntity {
                private int unit_id;
                private String unit_name;
                private String price;
                private String unit_img;
                private int unit_nums;
                private String unit_type;
                private String order_unit_id;

                public String getOrder_unit_id() {
                    return order_unit_id;
                }

                public void setOrder_unit_id(String order_unit_id) {
                    this.order_unit_id = order_unit_id;
                }

                public int getUnit_id() {
                    return unit_id;
                }

                public void setUnit_id(int unit_id) {
                    this.unit_id = unit_id;
                }

                public String getUnit_name() {
                    return unit_name;
                }

                public void setUnit_name(String unit_name) {
                    this.unit_name = unit_name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getUnit_img() {
                    return unit_img;
                }

                public void setUnit_img(String unit_img) {
                    this.unit_img = unit_img;
                }

                public int getUnit_nums() {
                    return unit_nums;
                }

                public void setUnit_nums(int unit_nums) {
                    this.unit_nums = unit_nums;
                }

                public String getUnit_type() {
                    return unit_type;
                }

                public void setUnit_type(String unit_type) {
                    this.unit_type = unit_type;
                }
            }
        }
    }
}
