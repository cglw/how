package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.v8.RefundEntity;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class OrderInfo extends BaseModel {


    /**
     * order_number : 161102384353718
     * order_number_parent : 161102605143715
     * order_status : 1
     * ship_status : 0
     * user_id : 1085
     * seller_id : 1135
     * get_uname : 默认
     * user_remark : 64646466666
     * user_mobile :
     * user_address : 上海 上海 静安区 上海
     * pay_type : 1
     * total_price : 692.00
     * amount_price : 680.34
     * coupon_price : 11.66
     * active_price : 0.00
     * coupon_code_id : 59047
     * coupon_str : FGOP7BME
     * ship_company :
     * ship_number :
     * unit_num : 3
     * create_time : 2016-11-02 17:54:30
     * ship_time : null
     * agree_cancel : 0
     * seller_name : 兔牙小姐
     * seller_headimg : http://img.prettyyes.com/1135-7703-1449477701.jpeg
     * unit_list : {TYPE_SUIT:[{"suit_id":2313,"suit_name":"慵懒随性套装","cover_image":"http://img.prettyyes.com/1135-8950-1445842045.jpeg","suit_unit":[{"suit_id":2313,"unit_id":3282,"unit_name":"包","price":"126.00","unit_img":"http://img.prettyyes.com/1135-2304-1445841878.jpeg","unit_nums":1},{"suit_id":2313,"unit_id":3283,"unit_name":"马丁靴","price":"298.00","unit_img":"http://img.prettyyes.com/1135-1322-1445842017.jpeg","unit_nums":1}]}],TYPE_TAOBAO:[{"unit_id":86,"unit_name":"MIUCO女装2016秋季新款通勤OL小西服拼接条纹衬衫裙假两件连衣裙","price":"268.00","unit_img":"http://img.alicdn.com/imgextra/i1/671012022/TB2s1FrXrDD11BjSszfXXbwoFXa_!!671012022.jpg","unit_nums":1}]}
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
        private String order_number_parent;
        private String order_status;
        private String ship_status;
        private int user_id;
        private int seller_id;
        private String get_uname;
        private String user_remark;
        private String user_mobile;
        private String user_address;
        private String pay_type;
        private String total_price;
        private String amount_price;
        private String coupon_price;
        private String active_price;
        private int coupon_code_id;
        private String coupon_str;
        private String coupon_name;
        private String coupon_txt;
        private String ship_company;
        private String ship_number;
        private int unit_num;
        private String create_time;
        private String ship_time;
        private String agree_cancel;
        private String seller_name;
        private String seller_headimg;
        private String seller_ace_txt;
        private int seller_famous=0;
        private UnitListEntity unit_list;
        public String grade_title;
        private RefundEntity refund;

        public RefundEntity getRefund() {
            return refund;
        }

        @Override
        public String toString() {
            return "InfoEntity{" +
                    "order_number='" + order_number + '\'' +
                    ", order_number_parent='" + order_number_parent + '\'' +
                    ", order_status='" + order_status + '\'' +
                    ", ship_status='" + ship_status + '\'' +
                    ", user_id=" + user_id +
                    ", seller_id=" + seller_id +
                    ", get_uname='" + get_uname + '\'' +
                    ", user_remark='" + user_remark + '\'' +
                    ", user_mobile='" + user_mobile + '\'' +
                    ", user_address='" + user_address + '\'' +
                    ", pay_type='" + pay_type + '\'' +
                    ", total_price='" + total_price + '\'' +
                    ", amount_price='" + amount_price + '\'' +
                    ", coupon_price='" + coupon_price + '\'' +
                    ", active_price='" + active_price + '\'' +
                    ", coupon_code_id=" + coupon_code_id +
                    ", coupon_str='" + coupon_str + '\'' +
                    ", coupon_name='" + coupon_name + '\'' +
                    ", coupon_txt='" + coupon_txt + '\'' +
                    ", ship_company='" + ship_company + '\'' +
                    ", ship_number='" + ship_number + '\'' +
                    ", unit_num=" + unit_num +
                    ", create_time='" + create_time + '\'' +
                    ", ship_time='" + ship_time + '\'' +
                    ", agree_cancel='" + agree_cancel + '\'' +
                    ", seller_name='" + seller_name + '\'' +
                    ", seller_headimg='" + seller_headimg + '\'' +
                    ", seller_ace_txt='" + seller_ace_txt + '\'' +
                    ", seller_famous=" + seller_famous +
                    ", unit_list=" + unit_list +
                    ", grade_title='" + grade_title + '\'' +
                    ", is_comment='" + is_comment + '\'' +
                    ", myListInfos=" + myListInfos +
                    '}';
        }

        public int getSeller_famous() {
            return seller_famous;
        }

        public void setSeller_famous(int seller_famous) {
            this.seller_famous = seller_famous;
        }

        public String getSeller_ace_txt() {
            return seller_ace_txt;
        }

        public void setSeller_ace_txt(String seller_ace_txt) {
            this.seller_ace_txt = seller_ace_txt;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public String getCoupon_txt() {
            return coupon_txt;
        }

        public void setCoupon_txt(String coupon_txt) {
            this.coupon_txt = coupon_txt;
        }

        private String is_comment;

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        private List<MyListInfo> myListInfos;

        public static class MyListInfo {
            private String suit_name;
            private int id;
            private String name;
            private String price;
            private String img;
            private int nums;
            private String type;
            private int suit_id;

            public int getSuit_id() {
                return suit_id;
            }

            public void setSuit_id(int suit_id) {
                this.suit_id = suit_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getNums() {
                return nums;
            }

            public void setNums(int nums) {
                this.nums = nums;
            }

            public String getSuit_name() {
                return suit_name;
            }

            public void setSuit_name(String suit_name) {
                this.suit_name = suit_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public List<MyListInfo> getMyListInfos() {
            return myListInfos;
        }

        public void setMyListInfos(List<MyListInfo> myListInfos) {
            this.myListInfos = myListInfos;
        }

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

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getGet_uname() {
            return get_uname;
        }

        public void setGet_uname(String get_uname) {
            this.get_uname = get_uname;
        }

        public String getUser_remark() {
            return user_remark;
        }

        public void setUser_remark(String user_remark) {
            this.user_remark = user_remark;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
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

        public int getCoupon_code_id() {
            return coupon_code_id;
        }

        public void setCoupon_code_id(int coupon_code_id) {
            this.coupon_code_id = coupon_code_id;
        }

        public String getCoupon_str() {
            return coupon_str;
        }

        public void setCoupon_str(String coupon_str) {
            this.coupon_str = coupon_str;
        }

        public String getShip_company() {
            return ship_company;
        }

        public void setShip_company(String ship_company) {
            this.ship_company = ship_company;
        }

        public String getShip_number() {
            return ship_number;
        }

        public void setShip_number(String ship_number) {
            this.ship_number = ship_number;
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

        public String getShip_time() {
            return ship_time;
        }

        public void setShip_time(String ship_time) {
            this.ship_time = ship_time;
        }

        public String getAgree_cancel() {
            return agree_cancel;
        }

        public void setAgree_cancel(String agree_cancel) {
            this.agree_cancel = agree_cancel;
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

        public UnitListEntity getUnit_list() {
            return unit_list;
        }

        public void setUnit_list(UnitListEntity unit_list) {
            this.unit_list = unit_list;
        }

        public static class UnitListEntity {
            /**
             * suit_id : 2313
             * suit_name : 慵懒随性套装
             * cover_image : http://img.prettyyes.com/1135-8950-1445842045.jpeg
             * suit_unit : [{"suit_id":2313,"unit_id":3282,"unit_name":"包","price":"126.00","unit_img":"http://img.prettyyes.com/1135-2304-1445841878.jpeg","unit_nums":1},{"suit_id":2313,"unit_id":3283,"unit_name":"马丁靴","price":"298.00","unit_img":"http://img.prettyyes.com/1135-1322-1445842017.jpeg","unit_nums":1}]
             */

            private List<SuitEntity> suit;
            /**
             * unit_id : 86
             * unit_name : MIUCO女装2016秋季新款通勤OL小西服拼接条纹衬衫裙假两件连衣裙
             * price : 268.00
             * unit_img : http://img.alicdn.com/imgextra/i1/671012022/TB2s1FrXrDD11BjSszfXXbwoFXa_!!671012022.jpg
             * unit_nums : 1
             */

            private List<TaobaoEntity> taobao;

            public List<SuitEntity> getSuit() {
                return suit;
            }

            public void setSuit(List<SuitEntity> suit) {
                this.suit = suit;
            }

            public List<TaobaoEntity> getTaobao() {
                return taobao;
            }

            public void setTaobao(List<TaobaoEntity> taobao) {
                this.taobao = taobao;
            }

            public static class SuitEntity {
                private int suit_id;
                private String order_unit_id;
                private String suit_name;
                private String cover_image;

                public String getOrder_unit_id() {
                    return order_unit_id;
                }

                public void setOrder_unit_id(String order_unit_id) {
                    this.order_unit_id = order_unit_id;
                }

                /**
                 * suit_id : 2313
                 * unit_id : 3282
                 * unit_name : 包
                 * price : 126.00
                 * unit_img : http://img.prettyyes.com/1135-2304-1445841878.jpeg
                 * unit_nums : 1
                 */

                private List<SuitUnitEntity> suit_unit;

                public int getSuit_id() {
                    return suit_id;
                }

                public void setSuit_id(int suit_id) {
                    this.suit_id = suit_id;
                }

                public String getSuit_name() {
                    return suit_name;
                }

                public void setSuit_name(String suit_name) {
                    this.suit_name = suit_name;
                }

                public String getCover_image() {
                    return cover_image;
                }

                public void setCover_image(String cover_image) {
                    this.cover_image = cover_image;
                }

                public List<SuitUnitEntity> getSuit_unit() {
                    return suit_unit;
                }

                public void setSuit_unit(List<SuitUnitEntity> suit_unit) {
                    this.suit_unit = suit_unit;
                }

                public static class SuitUnitEntity {
                    private int suit_id;
                    private int unit_id;
                    private String unit_name;
                    private String price;
                    private String unit_img;
                    private int unit_nums;

                    public int getSuit_id() {
                        return suit_id;
                    }

                    public void setSuit_id(int suit_id) {
                        this.suit_id = suit_id;
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
                }
            }

            public static class TaobaoEntity {
                private int unit_id;
                private String order_unit_id;
                private String unit_name;
                private String price;
                private String unit_img;
                private int unit_nums;

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
            }
        }
    }

}
