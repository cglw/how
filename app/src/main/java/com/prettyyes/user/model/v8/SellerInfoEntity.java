package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/11/7
 * Description: Nothing
 */
public class SellerInfoEntity extends BaseModel {

    /**
     * id : 1811
     * is_ace : 1
     * ace_txt : 老板一个负责设计一个负责摄影，找她搭配还能手把手教拍照。
     * nickname : @请叫我大钟
     * like_num : 1
     * fresh_ratio : 88
     * purchase_ratio : 89
     * satisfaction_ratio : 80
     * ace_img : http://img.prettyyes.com/1811-8265-1452499663.jpeg
     * headimg : http://img.prettyyes.com/1811-6961-1452072821.jpeg
     */

    private int id;
    private int is_ace;
    private String ace_txt;
    private String nickname;
    private String like_num;
    private String fresh_ratio;
    private String purchase_ratio;
    private String satisfaction_ratio;
    private String ace_img;
    private String headimg;
    private String session_ace_txt;
    private String seller_id;
    private String uid;
    private String gender;
    private String famous_type;
    private int is_like;
    private List<String> ingredient;
    private boolean isselect = false;
    private int is_invite;
    public String grade_title;

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getIs_invite() {
        return is_invite;
    }

    public void setIs_invite(int is_invite) {
        this.is_invite = is_invite;
    }

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

    public String getFamous_type() {
        return famous_type;
    }

    public boolean isFamous() {
        if (famous_type == null)
            return false;
        if (famous_type.equals("1"))
            return true;
        return false;
    }

    public void setFamous_type(String famous_type) {
        this.famous_type = famous_type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getSession_ace_txt() {
        return session_ace_txt;
    }

    public void setSession_ace_txt(String session_ace_txt) {
        this.session_ace_txt = session_ace_txt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_ace() {
        return is_ace;
    }

    public void setIs_ace(int is_ace) {
        this.is_ace = is_ace;
    }

    public String getAce_txt() {
        return ace_txt;
    }

    public void setAce_txt(String ace_txt) {
        this.ace_txt = ace_txt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getFresh_ratio() {
        return fresh_ratio;
    }

    public void setFresh_ratio(String fresh_ratio) {
        this.fresh_ratio = fresh_ratio;
    }

    public String getPurchase_ratio() {
        return purchase_ratio;
    }

    public void setPurchase_ratio(String purchase_ratio) {
        this.purchase_ratio = purchase_ratio;
    }

    public String getSatisfaction_ratio() {
        return satisfaction_ratio;
    }

    public void setSatisfaction_ratio(String satisfaction_ratio) {
        this.satisfaction_ratio = satisfaction_ratio;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAce_img() {
        return ace_img;
    }

    public void setAce_img(String ace_img) {
        this.ace_img = ace_img;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
