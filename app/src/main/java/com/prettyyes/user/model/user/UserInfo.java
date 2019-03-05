package com.prettyyes.user.model.user;

import com.prettyyes.user.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: 用户信息
 */
public class UserInfo extends BaseModel {

    /**
     * uuid : c36cc2c51227e7465383673d09766f11
     * username : 13911939045
     * nickname : 喵乎乎乎
     * realname :
     * telephone : 13911939045
     * headimg : http://img.prettyyes.com/31451-2372-1465297410.jpeg
     * msg_device_token : c31e9f54a778ff314fa08ad3b07c3668178f24cb2a24faabd7cd3ece9ccb4a23
     * grade : 1
     * type : 1
     * create_time : 2016-06-07 16:42:41
     * email :
     * gender : 2
     * information : 资深高级订制设计师，心理咨询师，爱猫咪的秤子，善解人意喵意，没人比我更懂你。
     * weight : 0
     * height : 0
     * age : 0
     * body_type : 0
     * tags_info : [{"tag_name":"清新","tag_id":72},{"tag_name":"欧美","tag_id":90},{"tag_name":"复古","tag_id":92},{"tag_name":"优雅","tag_id":98},{"tag_name":"OL","tag_id":111},{"tag_name":"休闲","tag_id":126},{"tag_name":"简约","tag_id":385},{"tag_name":"艺术气质","tag_id":1335},{"tag_name":"不着痕迹的时尚","tag_id":1338},{"tag_name":"不忙从 有态度","tag_id":1339}]
     */
    private String uuid;
    public String ace_txt;
    private String username;
    private String nickname;
    private String realname;
    private String telephone;
    private String headimg;
    private int famous_type;
    private String msg_device_token;
    private int grade;
    private int type;
    private String create_time;
    private String email;
    private int gender;
    private String information;
    private int weight;
    private int height;
    private int age;
    private String body_type;
    private String constellation;
    private String province;
    private String city;
    private String district;
    private int follow_num;
    private int like_num;
    private int answer_like_num;
    private int answer_dislike_num;
    private int share_num;
    private String pay_order_num;
    private String seller_status;
    private String seller_type;
    private String seller_level_name;
    private String seller_level;
    private String seller_score;
    private String brand;
    private List<TagsInfoBean> tags_info;
    private List<SellerTemplateBean> seller_template;
    public String user_id;
    public String score;
    public String grade_title;
    private int alert_message;
    private boolean redirect_bind_page;
    private String uid;
    private List<String> spu_img;
    private String spu_count;
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    private String getTest() {
        return test;
    }

    public String getAce_txt() {
        return ace_txt;
    }

    public void setAce_txt(String ace_txt) {
        this.ace_txt = ace_txt;
    }

    public String getSpu_count() {
        return spu_count;
    }

    public void setSpu_count(String spu_count) {
        this.spu_count = spu_count;
    }

    public List<String> getSpu_img() {
        return spu_img;
    }

    public void setSpu_img(List<String> spu_img) {
        this.spu_img = spu_img;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isRedirect_bind_page() {
        return redirect_bind_page;
    }

    public void setRedirect_bind_page(boolean redirect_bind_page) {
        this.redirect_bind_page = redirect_bind_page;
    }

    public int getAlert_message() {
        return alert_message;
    }

    public void setAlert_message(int alert_message) {
        this.alert_message = alert_message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * rongyun_token : {"rongyun_buyer":"YH29J02gGIzET0oaJYPvkwgVo+gclONvZDta++eSGFjCWiFnCF2lzknTwnV/nyFwJas3NV/bFtvOgvnjDF8Ulg==","rongyun_seller":"2TV4S5G0VCb6xUnfGxnlZPCUlScornVx6DXdzlreXkaqk/vLtnpMjMpAa4e33gpHFZWpThPRvqBZMvV9hGeSa9O/DLw/nfsE"}
     */

    public RongyunTokenBean rongyun_token;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(int famous_type) {
        this.famous_type = famous_type;
    }

    public String getMsg_device_token() {
        return msg_device_token;
    }

    public void setMsg_device_token(String msg_device_token) {
        this.msg_device_token = msg_device_token;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getAnswer_like_num() {
        return answer_like_num;
    }

    public void setAnswer_like_num(int answer_like_num) {
        this.answer_like_num = answer_like_num;
    }

    public int getAnswer_dislike_num() {
        return answer_dislike_num;
    }

    public void setAnswer_dislike_num(int answer_dislike_num) {
        this.answer_dislike_num = answer_dislike_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public String getPay_order_num() {
        return pay_order_num;
    }

    public void setPay_order_num(String pay_order_num) {
        this.pay_order_num = pay_order_num;
    }

    public String getSeller_level_name() {
        return seller_level_name;
    }

    public void setSeller_level_name(String seller_level_name) {
        this.seller_level_name = seller_level_name;
    }

    public String getSeller_level() {
        return seller_level;
    }

    public void setSeller_level(String seller_level) {
        this.seller_level = seller_level;
    }

    public String getSeller_score() {
        return seller_score;
    }

    public void setSeller_score(String seller_score) {
        this.seller_score = seller_score;
    }

    public List<TagsInfoBean> getTags_info() {
        return tags_info;
    }

    public void setTags_info(List<TagsInfoBean> tags_info) {
        this.tags_info = tags_info;
    }

    public List<SellerTemplateBean> getSeller_template() {
        return seller_template;
    }

    public void setSeller_template(List<SellerTemplateBean> seller_template) {
        this.seller_template = seller_template;
    }

    public RongyunTokenBean getRongyun_token() {
        return rongyun_token;
    }

    public void setRongyun_token(RongyunTokenBean rongyun_token) {
        this.rongyun_token = rongyun_token;
    }

    public static class TagsInfoBean implements Serializable {
        /**
         * tag_name : 清新
         * tag_id : 72
         */

        private String tag_name;
        private int tag_id;

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }
    }

    public static class SellerTemplateBean implements Serializable {
        /**
         * style : suit
         * status : 1
         */

        private String style;
        private int status;

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "SellerTemplateBean{" +
                    "style='" + style + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public static class RongyunTokenBean {
        /**
         * rongyun_buyer : YH29J02gGIzET0oaJYPvkwgVo+gclONvZDta++eSGFjCWiFnCF2lzknTwnV/nyFwJas3NV/bFtvOgvnjDF8Ulg==
         * rongyun_seller : 2TV4S5G0VCb6xUnfGxnlZPCUlScornVx6DXdzlreXkaqk/vLtnpMjMpAa4e33gpHFZWpThPRvqBZMvV9hGeSa9O/DLw/nfsE
         */

        private String rongyun_buyer;
        private String rongyun_seller;

        public String getRongyun_buyer() {
            return rongyun_buyer;
        }

        public void setRongyun_buyer(String rongyun_buyer) {
            this.rongyun_buyer = rongyun_buyer;
        }

        public String getRongyun_seller() {
            return rongyun_seller;
        }

        public void setRongyun_seller(String rongyun_seller) {
            this.rongyun_seller = rongyun_seller;
        }


    }

    public String getSeller_status() {
        return seller_status;
    }

    public void setSeller_status(String seller_status) {
        this.seller_status = seller_status;
    }

    public String getSeller_type() {
        return seller_type;
    }

    public boolean isSeller() {
        return ("1").equals(seller_type);
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private String newbie_task;

    public String getNewbie_task() {
        return newbie_task;
    }

    public void setNewbie_task(String newbie_task) {
        this.newbie_task = newbie_task;
    }

    public boolean isCompletetNewBie() {
        return newbie_task != null && newbie_task.length() > 0;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uuid='" + uuid + '\'' +
                ", ace_txt='" + ace_txt + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", headimg='" + headimg + '\'' +
                ", famous_type=" + famous_type +
                ", msg_device_token='" + msg_device_token + '\'' +
                ", grade=" + grade +
                ", type=" + type +
                ", create_time='" + create_time + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", information='" + information + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", body_type='" + body_type + '\'' +
                ", constellation='" + constellation + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", follow_num=" + follow_num +
                ", like_num=" + like_num +
                ", answer_like_num=" + answer_like_num +
                ", answer_dislike_num=" + answer_dislike_num +
                ", share_num=" + share_num +
                ", pay_order_num='" + pay_order_num + '\'' +
                ", seller_status='" + seller_status + '\'' +
                ", seller_type='" + seller_type + '\'' +
                ", seller_level_name='" + seller_level_name + '\'' +
                ", seller_level='" + seller_level + '\'' +
                ", seller_score='" + seller_score + '\'' +
                ", brand='" + brand + '\'' +
                ", tags_info=" + tags_info +
                ", seller_template=" + seller_template +
                ", user_id='" + user_id + '\'' +
                ", score='" + score + '\'' +
                ", grade_title='" + grade_title + '\'' +
                ", alert_message=" + alert_message +
                ", redirect_bind_page=" + redirect_bind_page +
                ", uid='" + uid + '\'' +
                ", spu_img=" + spu_img +
                ", spu_count='" + spu_count + '\'' +
                ", test='" + test + '\'' +
                ", rongyun_token=" + rongyun_token +
                '}';
    }
}
