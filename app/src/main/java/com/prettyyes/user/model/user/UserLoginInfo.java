package com.prettyyes.user.model.user;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/27
 * Description: Nothing
 */
public class UserLoginInfo extends BaseModel{


    /**
     * uuid : f76cdaf0c90cdb80d0d89a9073215683
     * username : 18356214885
     * nickname : py_1477815152
     * realname :
     * headimg : http://img.prettyyes.com/system/head_w.png
     * telephone : 18356214885
     * msg_device_token : 2e83b7627f2a6a8acea6c41b0f5068fb
     * grade : 1
     * type : 1
     * seller_type : 0
     * gender : 2
     * email :
     * tags_info : [{"tag_name":"搭配好手","tag_id":247},{"tag_name":"挑货高手","tag_id":248}]
     * rongyun_token : {"rongyun_buyer":"jJ1u4sk6zyNZ3Ze/rhnxoOiUwUxYvDruppVS6JfFR2JfvE4Vsc3+5Ud8xWOMoa1EsG5EMAB8nxwwioTwotshqA==","rongyun_seller":""}
     */


    private int alert_message;

    public int getAlert_message() {
        return alert_message;
    }

    public void setAlert_message(int alert_message) {
        this.alert_message = alert_message;
    }

    private String uuid;
    private String username;
    private String nickname;
    private String realname;
    private String headimg;
    private String telephone;
    private String msg_device_token;
    private int grade;
    private int type;
    private int seller_type;
    private int gender;
    private String email;
    private boolean redirect_bind_page;

    public boolean isRedirect_bind_page() {
        return redirect_bind_page;
    }

    public void setRedirect_bind_page(boolean redirect_bind_page) {
        this.redirect_bind_page = redirect_bind_page;
    }

    /**
     * rongyun_buyer : jJ1u4sk6zyNZ3Ze/rhnxoOiUwUxYvDruppVS6JfFR2JfvE4Vsc3+5Ud8xWOMoa1EsG5EMAB8nxwwioTwotshqA==
     * rongyun_seller :
     */

    private RongyunTokenEntity rongyun_token;
    /**
     * tag_name : 搭配好手
     * tag_id : 247
     */

    private List<TagsInfoEntity> tags_info;

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

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public int getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(int seller_type) {
        this.seller_type = seller_type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RongyunTokenEntity getRongyun_token() {
        return rongyun_token;
    }

    public void setRongyun_token(RongyunTokenEntity rongyun_token) {
        this.rongyun_token = rongyun_token;
    }

    public List<TagsInfoEntity> getTags_info() {
        return tags_info;
    }

    public void setTags_info(List<TagsInfoEntity> tags_info) {
        this.tags_info = tags_info;
    }

    public static class RongyunTokenEntity {
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

        @Override
        public String toString() {
            return "RongyunTokenEntity{" +
                    "rongyun_buyer='" + rongyun_buyer + '\'' +
                    ", rongyun_seller='" + rongyun_seller + '\'' +
                    '}';
        }
    }

    public static class TagsInfoEntity {
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

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "alert_message=" + alert_message +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", headimg='" + headimg + '\'' +
                ", telephone='" + telephone + '\'' +
                ", msg_device_token='" + msg_device_token + '\'' +
                ", grade=" + grade +
                ", type=" + type +
                ", seller_type=" + seller_type +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", redirect_bind_page=" + redirect_bind_page +
                ", rongyun_token=" + rongyun_token +
                ", tags_info=" + tags_info +
                '}';
    }
}
