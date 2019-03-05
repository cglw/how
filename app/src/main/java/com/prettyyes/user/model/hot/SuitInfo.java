package com.prettyyes.user.model.hot;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.hot
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SuitInfo extends BaseModel{

    /**
     * suit_id : 4830
     * recommend :
     * name : 几何花纹
     * uid : 2545
     * cover_img : http://img.prettyyes.com/2545-8288-1456930590.jpeg
     * like_num : 0
     * desc : 民族风几何图案元素显瘦无袖衫套装，复古时尚简约剪裁，能展现好身材，适合上班约会逛街闺蜜聚会公司活动等场合。
     * bright_point : 民族几何图案元素是亮点，棉花混合简约剪裁，高腰设计修饰腿型效果佳，搭配极简高跟鞋，人造革面，手工鞋底，带扣束缚，性感不止一点点。
     * rubbish : 小肚肚有肉的姑凉慎重下手。
     * create_time : 2016-03-02 22:59:01
     * status : 3
     * price : 2397.00
     * video_src :
     * suit_img : http://img.prettyyes.com/2545-8288-1456930590.jpeg;http://img.prettyyes.com/2545-6179-1456930590.jpeg;http://img.prettyyes.com/2545-5725-1456930599.jpeg;http://img.prettyyes.com/2545-8027-1456930600.jpeg;http://img.prettyyes.com/2545-3199-1456930813.jpeg;http://img.prettyyes.com/2545-2711-1456930820.jpeg
     * unit_detail : [  {    "unit_name" : "民族花纹套装",    "img" : "http://img.prettyyes.com/2545-1397-1456930447.jpeg",    "price" : 1498,    "desc" : "民族花纹套装",    "unit_id" : 12511  },  {    "unit_name" : "简约高跟鞋",    "img" : "http://img.prettyyes.com/2545-6248-1456930518.jpeg",    "price" : 899,    "desc" : "简约高跟鞋",    "unit_id" : 12512  }]
     * unit_list : [{"unit_id":12529,"unit_name":"民族花纹套装","desc":"民族花纹套装","price":"1498.00","img":"http://img.prettyyes.com/2545-1397-1456930447.jpeg"},{"unit_id":12530,"unit_name":"简约高跟鞋","desc":"简约高跟鞋","price":"899.00","img":"http://img.prettyyes.com/2545-6248-1456930518.jpeg"}]
     * tags_info : [{"tag_name":"欧美","tag_id":90,"suit_id":4830},{"tag_name":"休闲","tag_id":126,"suit_id":4830},{"tag_name":"OL","tag_id":111,"suit_id":4830},{"tag_name":"优雅","tag_id":98,"suit_id":4830},{"tag_name":"简约","tag_id":385,"suit_id":4830}]
     * is_like : no
     * seller : {"username":"17713603334","nickname":"Chan","realname":"","telephone":"17713603334","headimg":"http://img.prettyyes.com/2545-1414-1456546129.jpeg","grade":5,"tags_info":[{"tag_name":"休闲","tag_id":126},{"tag_name":"简约","tag_id":385},{"tag_name":"通勤","tag_id":93},{"tag_name":"欧美","tag_id":90}],"reserve":0,"reserve_area":null}
     * recommend_goods : [{"goods_id":23,"goods_image":"http://img.prettyyes.com/9-2381-1449125255.jpeg","goods_name":"test","goods_desc":"test","goods_price":"11.00","goods_link":"","goods_tags":[{"tag_id":238,"tag_name":"test","hot_num":0}],"create_time":"2015-09-30 14:39:31","status":"open","gift":"0","param":null},{"goods_id":22,"goods_image":"http://img.prettyyes.com/9-9597-1449125268.jpeg","goods_name":"已经成功加入肯德基豪华午餐","goods_desc":"","goods_price":"200.00","goods_link":"","goods_tags":[],"create_time":"2015-09-30 10:11:07","status":"open","gift":"0","param":null}]
     */

    private InfoEntity info;

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        private int suit_id;
        private String recommend;
        private String name;
        private int uid;
        private String cover_img;
        private int like_num;
        private String desc;
        private String bright_point;
        private String rubbish;
        private String create_time;
        private int status;
        private String price;
        private String video_src;
        private String suit_img;
        private String unit_detail;
        private String is_like;
        /**
         * username : 17713603334
         * nickname : Chan
         * realname :
         * telephone : 17713603334
         * headimg : http://img.prettyyes.com/2545-1414-1456546129.jpeg
         * grade : 5
         * tags_info : [{"tag_name":"休闲","tag_id":126},{"tag_name":"简约","tag_id":385},{"tag_name":"通勤","tag_id":93},{"tag_name":"欧美","tag_id":90}]
         * reserve : 0
         * reserve_area : null
         */

        private SellerEntity seller;
        /**
         * unit_id : 12529
         * unit_name : 民族花纹套装
         * desc : 民族花纹套装
         * price : 1498.00
         * img : http://img.prettyyes.com/2545-1397-1456930447.jpeg
         */

        private List<UnitListEntity> unit_list;
        /**
         * tag_name : 欧美
         * tag_id : 90
         * suit_id : 4830
         */

        private List<TagsInfoEntity> tags_info;
        /**
         * goods_id : 23
         * goods_image : http://img.prettyyes.com/9-2381-1449125255.jpeg
         * goods_name : test
         * goods_desc : test
         * goods_price : 11.00
         * goods_link :
         * goods_tags : [{"tag_id":238,"tag_name":"test","hot_num":0}]
         * create_time : 2015-09-30 14:39:31
         * status : open
         * gift : 0
         * param : null
         */

        private List<RecommendGoodsEntity> recommend_goods;

        public void setSuit_id(int suit_id) {
            this.suit_id = suit_id;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setBright_point(String bright_point) {
            this.bright_point = bright_point;
        }

        public void setRubbish(String rubbish) {
            this.rubbish = rubbish;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setVideo_src(String video_src) {
            this.video_src = video_src;
        }

        public void setSuit_img(String suit_img) {
            this.suit_img = suit_img;
        }

        public void setUnit_detail(String unit_detail) {
            this.unit_detail = unit_detail;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }

        public void setSeller(SellerEntity seller) {
            this.seller = seller;
        }

        public void setUnit_list(List<UnitListEntity> unit_list) {
            this.unit_list = unit_list;
        }

        public void setTags_info(List<TagsInfoEntity> tags_info) {
            this.tags_info = tags_info;
        }

        public void setRecommend_goods(List<RecommendGoodsEntity> recommend_goods) {
            this.recommend_goods = recommend_goods;
        }

        public int getSuit_id() {
            return suit_id;
        }

        public String getRecommend() {
            return recommend;
        }

        public String getName() {
            return name;
        }

        public int getUid() {
            return uid;
        }

        public String getCover_img() {
            return cover_img;
        }

        public int getLike_num() {
            return like_num;
        }

        public String getDesc() {
            return desc;
        }

        public String getBright_point() {
            return bright_point;
        }

        public String getRubbish() {
            return rubbish;
        }

        public String getCreate_time() {
            return create_time;
        }

        public int getStatus() {
            return status;
        }

        public String getPrice() {
            return price;
        }

        public String getVideo_src() {
            return video_src;
        }

        public String getSuit_img() {
            return suit_img;
        }

        public String getUnit_detail() {
            return unit_detail;
        }

        public String getIs_like() {
            return is_like;
        }

        public SellerEntity getSeller() {
            return seller;
        }

        public List<UnitListEntity> getUnit_list() {
            return unit_list;
        }

        public List<TagsInfoEntity> getTags_info() {
            return tags_info;
        }

        public List<RecommendGoodsEntity> getRecommend_goods() {
            return recommend_goods;
        }

        public static class SellerEntity {
            private String username;
            private String nickname;
            private String realname;
            private String telephone;
            private String headimg;
            private int grade;
            private int reserve;
            private Object reserve_area;
            /**
             * tag_name : 休闲
             * tag_id : 126
             */

            private List<TagsInfoEntity> tags_info;

            public void setUsername(String username) {
                this.username = username;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public void setReserve(int reserve) {
                this.reserve = reserve;
            }

            public void setReserve_area(Object reserve_area) {
                this.reserve_area = reserve_area;
            }

            public void setTags_info(List<TagsInfoEntity> tags_info) {
                this.tags_info = tags_info;
            }

            public String getUsername() {
                return username;
            }

            public String getNickname() {
                return nickname;
            }

            public String getRealname() {
                return realname;
            }

            public String getTelephone() {
                return telephone;
            }

            public String getHeadimg() {
                return headimg;
            }

            public int getGrade() {
                return grade;
            }

            public int getReserve() {
                return reserve;
            }

            public Object getReserve_area() {
                return reserve_area;
            }

            public List<TagsInfoEntity> getTags_info() {
                return tags_info;
            }

            public static class TagsInfoEntity {
                private String tag_name;
                private int tag_id;

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public void setTag_id(int tag_id) {
                    this.tag_id = tag_id;
                }

                public String getTag_name() {
                    return tag_name;
                }

                public int getTag_id() {
                    return tag_id;
                }
            }
        }

        public static class UnitListEntity {
            private int unit_id;
            private String unit_name;
            private String desc;
            private String price;
            private String img;

            public void setUnit_id(int unit_id) {
                this.unit_id = unit_id;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getUnit_id() {
                return unit_id;
            }

            public String getUnit_name() {
                return unit_name;
            }

            public String getDesc() {
                return desc;
            }

            public String getPrice() {
                return price;
            }

            public String getImg() {
                return img;
            }
        }

        public static class TagsInfoEntity {
            private String tag_name;
            private int tag_id;
            private int suit_id;

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public void setTag_id(int tag_id) {
                this.tag_id = tag_id;
            }

            public void setSuit_id(int suit_id) {
                this.suit_id = suit_id;
            }

            public String getTag_name() {
                return tag_name;
            }

            public int getTag_id() {
                return tag_id;
            }

            public int getSuit_id() {
                return suit_id;
            }
        }

        public static class RecommendGoodsEntity {
            private int goods_id;
            private String goods_image;
            private String goods_name;
            private String goods_desc;
            private String goods_price;
            private String goods_link;
            private String create_time;
            private String status;
            private String gift;
            private Object param;
            /**
             * tag_id : 238
             * tag_name : test
             * hot_num : 0
             */

            private List<GoodsTagsEntity> goods_tags;

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_link(String goods_link) {
                this.goods_link = goods_link;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setGift(String gift) {
                this.gift = gift;
            }

            public void setParam(Object param) {
                this.param = param;
            }

            public void setGoods_tags(List<GoodsTagsEntity> goods_tags) {
                this.goods_tags = goods_tags;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public String getGoods_link() {
                return goods_link;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getStatus() {
                return status;
            }

            public String getGift() {
                return gift;
            }

            public Object getParam() {
                return param;
            }

            public List<GoodsTagsEntity> getGoods_tags() {
                return goods_tags;
            }

            public static class GoodsTagsEntity {
                private int tag_id;
                private String tag_name;
                private int hot_num;

                public void setTag_id(int tag_id) {
                    this.tag_id = tag_id;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public void setHot_num(int hot_num) {
                    this.hot_num = hot_num;
                }

                public int getTag_id() {
                    return tag_id;
                }

                public String getTag_name() {
                    return tag_name;
                }

                public int getHot_num() {
                    return hot_num;
                }
            }
        }
    }
}
