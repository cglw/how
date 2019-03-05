package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.order
 * Author: SmileChen
 * Created on: 2016/10/26
 * Description: Nothing
 */
public class WishListEntity extends BaseModel {

    /**
     * seller_id : 1135
     * seller_name : 兔牙小姐
     * seller_headimg : http://img.prettyyes.com/1135-7703-1449477701.jpeg
     * suit : [{"seller_id":1135,"suit_id":2310,"suit_name":"简单时尚甜美系","cover_img":"http://img.prettyyes.com/1135-8758-1445840579.jpeg","suit_status":1,"suit_unit":[{"suit_id":2310,"unit_id":3268,"unit_name":"套头毛衣","price":"178.00","img":"http://img.prettyyes.com/1135-1706-1445840470.jpeg","num":1,"cart_id":46,"cart_status":1},{"suit_id":2310,"unit_id":3269,"unit_name":"裤子","price":"117.00","img":"http://img.prettyyes.com/1135-2990-1445840500.jpeg","num":1,"cart_id":47,"cart_status":1},{"suit_id":2310,"unit_id":3270,"unit_name":"包","price":"218.00","img":"http://img.prettyyes.com/1135-7884-1445840525.jpeg","num":1,"cart_id":48,"cart_status":1},{"suit_id":2310,"unit_id":3271,"unit_name":"单鞋","price":"289.00","img":"http://img.prettyyes.com/1135-5067-1445840552.jpeg","num":1,"cart_id":49,"cart_status":1}]},{"seller_id":1135,"suit_id":2313,"suit_name":"慵懒随性套装","cover_img":"http://img.prettyyes.com/1135-8950-1445842045.jpeg","suit_status":1,"suit_unit":[{"suit_id":2313,"unit_id":3282,"unit_name":"包","price":"126.00","img":"http://img.prettyyes.com/1135-2304-1445841878.jpeg","num":1,"cart_id":43,"cart_status":1}]}]
     * taobao : [{"cart_id":45,"cart_status":1,"seller_id":1135,"taobao_id":86,"title":"MIUCO女装2016秋季新款通勤OL小西服拼接条纹衬衫裙假两件连衣裙","main_image":"http://img.alicdn.com/imgextra/i1/671012022/TB2s1FrXrDD11BjSszfXXbwoFXa_!!671012022.jpg","price":"268.00","num":2}]
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private int seller_id;
        private String seller_name;
        private String seller_headimg;
        private int famous_type=0;

        public int getFamous_type() {
            return famous_type;
        }

        public void setFamous_type(int famous_type) {
            this.famous_type = famous_type;
        }

        /**
         * seller_id : 1135
         * suit_id : 2310
         * suit_name : 简单时尚甜美系
         * cover_img : http://img.prettyyes.com/1135-8758-1445840579.jpeg
         * suit_status : 1
         * suit_unit : [{"suit_id":2310,"unit_id":3268,"unit_name":"套头毛衣","price":"178.00","img":"http://img.prettyyes.com/1135-1706-1445840470.jpeg","num":1,"cart_id":46,"cart_status":1},{"suit_id":2310,"unit_id":3269,"unit_name":"裤子","price":"117.00","img":"http://img.prettyyes.com/1135-2990-1445840500.jpeg","num":1,"cart_id":47,"cart_status":1},{"suit_id":2310,"unit_id":3270,"unit_name":"包","price":"218.00","img":"http://img.prettyyes.com/1135-7884-1445840525.jpeg","num":1,"cart_id":48,"cart_status":1},{"suit_id":2310,"unit_id":3271,"unit_name":"单鞋","price":"289.00","img":"http://img.prettyyes.com/1135-5067-1445840552.jpeg","num":1,"cart_id":49,"cart_status":1}]
         */

        private List<SuitEntity> suit;
        /**
         * cart_id : 45
         * cart_status : 1
         * seller_id : 1135
         * taobao_id : 86
         * title : MIUCO女装2016秋季新款通勤OL小西服拼接条纹衬衫裙假两件连衣裙
         * main_image : http://img.alicdn.com/imgextra/i1/671012022/TB2s1FrXrDD11BjSszfXXbwoFXa_!!671012022.jpg
         * price : 268.00
         * num : 2
         */

        private List<TaobaoEntity> taobao;

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
            private int seller_id;
            private int suit_id;
            private String suit_name;
            private String cover_img;
            private int suit_status;
            /**
             * suit_id : 2310
             * unit_id : 3268
             * unit_name : 套头毛衣
             * price : 178.00
             * img : http://img.prettyyes.com/1135-1706-1445840470.jpeg
             * num : 1
             * cart_id : 46
             * cart_status : 1
             */

            private List<SuitUnitEntity> suit_unit;

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

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

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public int getSuit_status() {
                return suit_status;
            }

            public void setSuit_status(int suit_status) {
                this.suit_status = suit_status;
            }

            public List<SuitUnitEntity> getSuit_unit() {
                return suit_unit;
            }

            public void setSuit_unit(List<SuitUnitEntity> suit_unit) {
                this.suit_unit = suit_unit;
            }

            public static class SuitUnitEntity {
                @Override
                public String toString() {
                    return "SuitUnitEntity{" +
                            "suit_id=" + suit_id +
                            ", unit_id=" + unit_id +
                            ", unit_name='" + unit_name + '\'' +
                            ", price='" + price + '\'' +
                            ", img='" + img + '\'' +
                            ", num=" + num +
                            ", cart_id=" + cart_id +
                            ", cart_status=" + cart_status +
                            ", select=" + select +
                            '}';
                }

                private int suit_id;
                private int unit_id;
                private String unit_name;
                private String price;
                private String img;
                private int num;
                private int cart_id;
                private int cart_status;
                private boolean select=false;//是否被选中

                public boolean isSelect() {
                    return select;
                }

                public void setSelect(boolean select) {
                    this.select = select;
                }

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

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getCart_id() {
                    return cart_id;
                }

                public void setCart_id(int cart_id) {
                    this.cart_id = cart_id;
                }

                public int getCart_status() {
                    return cart_status;
                }

                public void setCart_status(int cart_status) {
                    this.cart_status = cart_status;
                }
            }
        }

        public static class TaobaoEntity {
            private int cart_id;
            private int cart_status;
            private int seller_id;
            private int taobao_id;
            private String title;
            private String main_image;
            private String price;
            private int num;

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public int getCart_status() {
                return cart_status;
            }

            public void setCart_status(int cart_status) {
                this.cart_status = cart_status;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public int getTaobao_id() {
                return taobao_id;
            }

            public void setTaobao_id(int taobao_id) {
                this.taobao_id = taobao_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMain_image() {
                return main_image;
            }

            public void setMain_image(String main_image) {
                this.main_image = main_image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
