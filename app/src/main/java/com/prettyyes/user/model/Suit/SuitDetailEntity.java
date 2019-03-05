package com.prettyyes.user.model.Suit;

import com.prettyyes.user.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.Suit
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SuitDetailEntity extends BaseModel {

    /**
     * id : 9797
     * suit_id : 9797
     * name : 名媛
     * uid : 21885
     * cover_img : http://img.prettyyes.com/21885-8766-1462348927.jpeg
     * like_num : 0
     * recommend :
     * desc : 大牌剪裁
     * 肩部特殊设计
     * 透视荷叶边
     * 宽松连衣裙
     * bright_point : 独特的肩部设计 给人视觉美
     * 特殊的面料穿起来非常舒服
     * 简直就是大牌 名媛气质
     * rubbish : 真是非常美的裙子 有品味的人当然不会错过
     * create_time : 2016-05-04 16:09:06
     * status : 1
     * price : 299.00
     * video_src :
     * suit_img : http://img.prettyyes.com/21885-8766-1462348927.jpeg;http://img.prettyyes.com/21885-9908-1462348927.jpeg;http://img.prettyyes.com/21885-2907-1462348927.jpeg;http://img.prettyyes.com/21885-8194-1462348927.jpeg;http://img.prettyyes.com/21885-3614-1462348949.jpeg
     * seller : {"uuid":"944e84d0c8e13c089dab5f05487ecedd","username":"15124622474","nickname":"YUHUANhuan＊","realname":"","gender":2,"email":"","telephone":"15124622474","seller_type":1,"is_ace":"1","ace_txt":"","ace_img":null,"ace_sort_num":0,"headimg":"http://img.prettyyes.com/system/head_w.png","information":"有品味的女人才是最美的 想要穿的有魅力就来找我吧","birth_date":"0000-00-00","height":0,"weight":0,"waistline":null,"body_type":null,"age":null,"is_email_verified":0,"grade":5,"seller_device_token":"2f071b3ab33e47ecb37286b260174999e85b6ed2590f899bb12a7fce89fce719","msg_badge":0,"create_time":"2016-04-21 11:04:31","tag":null,"is_open":"1","like_num":0,"openid":null,"unionid":null,"weibo_uid":null,"client":null,"like_color":null,"question":null,"area":null,"reserve":0,"seller_id":21885,"reserve_area":null}
     * unit_list : [{"unit_id":32072,"unit_name":"透视荷叶边连衣裙","desc":"透视荷叶边连衣裙","price":"299.00","img":"http://img.prettyyes.com/21885-7880-1462348874.jpeg"},{"unit_id":32073,"unit_name":"透视荷叶边连衣裙","desc":"透视荷叶边连衣裙","price":"299.00","img":"http://img.prettyyes.com/21885-7880-1462348874.jpeg"}]
     * tags_info : [{"tag_name":"独立设计师","tag_id":275,"suit_id":9797},{"tag_name":"简约","tag_id":385,"suit_id":9797}]
     * is_like : no
     * recommend_goods : [{"goods_id":23,"goods_image":"http://img.prettyyes.com/9-2381-1449125255.jpeg","goods_name":"test","goods_desc":"test","goods_price":"11.00","goods_link":"","goods_tags":[{"tag_id":238,"tag_name":"test","hot_num":0}],"create_time":"2015-09-30 14:39:31","status":"open","gift":"0","param":null},{"goods_id":22,"goods_image":"http://img.prettyyes.com/9-9597-1449125268.jpeg","goods_name":"已经成功加入肯德基豪华午餐","goods_desc":"","goods_price":"200.00","goods_link":"","goods_tags":[],"create_time":"2015-09-30 10:11:07","status":"open","gift":"0","param":null}]
     */

    private SuitEntity suit;

    public void setSuit(SuitEntity suit) {
        this.suit = suit;
    }

    public SuitEntity getSuit() {
        return suit;
    }

    public static class SuitEntity implements Serializable {
        private int id;
        private int suit_id;
        private String name;
        private int uid;
        private String cover_img;
        private int like_num;
        private String recommend;
        private String desc;
        private String bright_point;
        private String rubbish;
        private String create_time;
        private int status;
        private String price;
        private String video_src;
        private String suit_img;
        /**
         * uuid : 944e84d0c8e13c089dab5f05487ecedd
         * username : 15124622474
         * nickname : YUHUANhuan＊
         * realname :
         * gender : 2
         * email :
         * telephone : 15124622474
         * seller_type : 1
         * is_ace : 1
         * ace_txt :
         * ace_img : null
         * ace_sort_num : 0
         * headimg : http://img.prettyyes.com/system/head_w.png
         * information : 有品味的女人才是最美的 想要穿的有魅力就来找我吧
         * birth_date : 0000-00-00
         * height : 0
         * weight : 0
         * waistline : null
         * body_type : null
         * age : null
         * is_email_verified : 0
         * grade : 5
         * seller_device_token : 2f071b3ab33e47ecb37286b260174999e85b6ed2590f899bb12a7fce89fce719
         * msg_badge : 0
         * create_time : 2016-04-21 11:04:31
         * tag : null
         * is_open : 1
         * like_num : 0
         * openid : null
         * unionid : null
         * weibo_uid : null
         * client : null
         * like_color : null
         * question : null
         * area : null
         * reserve : 0
         * seller_id : 21885
         * reserve_area : null
         */

        private SellerEntity seller;
        private String is_like;
        private String wish_list;

        public boolean isAddwish_lish() {
            if (wish_list.equals("yes")) {
                return true;
            }
            return false;
        }

        /**
         * unit_id : 32072
         * unit_name : 透视荷叶边连衣裙
         * desc : 透视荷叶边连衣裙
         * price : 299.00
         * img : http://img.prettyyes.com/21885-7880-1462348874.jpeg
         */

        private List<UnitListEntity> unit_list;
        /**
         * tag_name : 独立设计师
         * tag_id : 275
         * suit_id : 9797
         */

        private List<TagsInfoEntity> tags_info;
        private String share_url;

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

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

        public void setId(int id) {
            this.id = id;
        }

        public void setSuit_id(int suit_id) {
            this.suit_id = suit_id;
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

        public void setRecommend(String recommend) {
            this.recommend = recommend;
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

        public void setSeller(SellerEntity seller) {
            this.seller = seller;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
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

        public int getId() {
            return id;
        }

        public int getSuit_id() {
            return suit_id;
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

        public String getRecommend() {
            return recommend;
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

        public SellerEntity getSeller() {
            return seller;
        }

        public String getIs_like() {
            return is_like;
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

        public static class SellerEntity implements Serializable {
            private String uuid;
            private String username;
            private String nickname;
            private String realname;
            private int gender;
            private String email;
            private String telephone;
            private int seller_type;
            private String is_ace;
            private String ace_txt;
            private Object ace_img;
            private int ace_sort_num;
            private String headimg;
            private String information;
            private String birth_date;
            private int height;
            private int weight;
            private Object waistline;
            private Object body_type;
            private Object age;
            private int is_email_verified;
            private int grade;
            private String seller_device_token;
            private int msg_badge;
            private String create_time;
            private Object tag;
            private String is_open;
            private int like_num;
            private Object openid;
            private Object unionid;
            private Object weibo_uid;
            private Object client;
            private Object like_color;
            private Object question;
            private Object area;
            private int reserve;
            private int seller_id;
            private Object reserve_area;
            private int famous_type = 0;

            public int getFamous_type() {
                return famous_type;
            }

            public void setFamous_type(int famous_type) {
                this.famous_type = famous_type;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setSeller_type(int seller_type) {
                this.seller_type = seller_type;
            }

            public void setIs_ace(String is_ace) {
                this.is_ace = is_ace;
            }

            public void setAce_txt(String ace_txt) {
                this.ace_txt = ace_txt;
            }

            public void setAce_img(Object ace_img) {
                this.ace_img = ace_img;
            }

            public void setAce_sort_num(int ace_sort_num) {
                this.ace_sort_num = ace_sort_num;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public void setInformation(String information) {
                this.information = information;
            }

            public void setBirth_date(String birth_date) {
                this.birth_date = birth_date;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public void setWaistline(Object waistline) {
                this.waistline = waistline;
            }

            public void setBody_type(Object body_type) {
                this.body_type = body_type;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public void setIs_email_verified(int is_email_verified) {
                this.is_email_verified = is_email_verified;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public void setSeller_device_token(String seller_device_token) {
                this.seller_device_token = seller_device_token;
            }

            public void setMsg_badge(int msg_badge) {
                this.msg_badge = msg_badge;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setTag(Object tag) {
                this.tag = tag;
            }

            public void setIs_open(String is_open) {
                this.is_open = is_open;
            }

            public void setLike_num(int like_num) {
                this.like_num = like_num;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public void setUnionid(Object unionid) {
                this.unionid = unionid;
            }

            public void setWeibo_uid(Object weibo_uid) {
                this.weibo_uid = weibo_uid;
            }

            public void setClient(Object client) {
                this.client = client;
            }

            public void setLike_color(Object like_color) {
                this.like_color = like_color;
            }

            public void setQuestion(Object question) {
                this.question = question;
            }

            public void setArea(Object area) {
                this.area = area;
            }

            public void setReserve(int reserve) {
                this.reserve = reserve;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public void setReserve_area(Object reserve_area) {
                this.reserve_area = reserve_area;
            }

            public String getUuid() {
                return uuid;
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

            public int getGender() {
                return gender;
            }

            public String getEmail() {
                return email;
            }

            public String getTelephone() {
                return telephone;
            }

            public int getSeller_type() {
                return seller_type;
            }

            public String getIs_ace() {
                return is_ace;
            }

            public String getAce_txt() {
                return ace_txt;
            }

            public Object getAce_img() {
                return ace_img;
            }

            public int getAce_sort_num() {
                return ace_sort_num;
            }

            public String getHeadimg() {
                return headimg;
            }

            public String getInformation() {
                return information;
            }

            public String getBirth_date() {
                return birth_date;
            }

            public int getHeight() {
                return height;
            }

            public int getWeight() {
                return weight;
            }

            public Object getWaistline() {
                return waistline;
            }

            public Object getBody_type() {
                return body_type;
            }

            public Object getAge() {
                return age;
            }

            public int getIs_email_verified() {
                return is_email_verified;
            }

            public int getGrade() {
                return grade;
            }

            public String getSeller_device_token() {
                return seller_device_token;
            }

            public int getMsg_badge() {
                return msg_badge;
            }

            public String getCreate_time() {
                return create_time;
            }

            public Object getTag() {
                return tag;
            }

            public String getIs_open() {
                return is_open;
            }

            public int getLike_num() {
                return like_num;
            }

            public Object getOpenid() {
                return openid;
            }

            public Object getUnionid() {
                return unionid;
            }

            public Object getWeibo_uid() {
                return weibo_uid;
            }

            public Object getClient() {
                return client;
            }

            public Object getLike_color() {
                return like_color;
            }

            public Object getQuestion() {
                return question;
            }

            public Object getArea() {
                return area;
            }

            public int getReserve() {
                return reserve;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public Object getReserve_area() {
                return reserve_area;
            }
        }

        public static class UnitListEntity implements Serializable {
            private int unit_id;
            private String unit_name;
            private String desc;
            private String price;
            private String img;
            private int num = 1;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            private boolean ischeck = true;

            public boolean ischeck() {
                return ischeck;
            }

            public void setIscheck(boolean ischeck) {
                this.ischeck = ischeck;
            }


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

        public static class TagsInfoEntity implements Serializable {
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

        public static class RecommendGoodsEntity implements Serializable {
            private int goods_id;
            private String goods_image;
            private String goods_name;
            private String goods_desc;
            private String goods_price;
            private String goods_link;
            private String create_time;
            private String status;
            private String gift;
            private String param;


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

            public void setParam(String param) {
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

            public String getParam() {
                return param;
            }

            public List<GoodsTagsEntity> getGoods_tags() {
                return goods_tags;
            }

            public static class GoodsTagsEntity implements Serializable {
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
