package com.prettyyes.user.model.common;

import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.other.SceneInfo;
import com.prettyyes.user.model.user.UserInfo;
import com.prettyyes.user.model.v8.AnswerInfoEntity;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.common
 * Author: SmileChen
 * Created on: 2016/12/20
 * Description: Nothing
 */
public class ReplyExtralModel extends BaseModel {
    private int taskid;
    private int uid;
    private String price_s;
    private String price_e;
    private String desc;
    private String pic_list;
    private String create_time;
    private String update_time;
    private int view_num;
    private int like_num;
    private int dislike_num;
    private int share_num;
    private int tip;
    private int excellent;
    private int pricecount;
    private int famous;
    private String Pscore;
    private String hash_tag;
    private String act_name;
    private String act_content;
    private String is_issue;
    private int self_task;
    private UserInfo userinfo;
    private SceneInfo scene;
    private List<AnswerInfoEntity> suit_list;

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_content() {
        return act_content;
    }

    public void setAct_content(String act_content) {
        this.act_content = act_content;
    }


    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPrice_s() {
        return price_s;
    }

    public void setPrice_s(String price_s) {
        this.price_s = price_s;
    }

    public String getPrice_e() {
        return price_e;
    }

    public void setPrice_e(String price_e) {
        this.price_e = price_e;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic_list() {
        return pic_list;
    }

    public void setPic_list(String pic_list) {
        this.pic_list = pic_list;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getDislike_num() {
        return dislike_num;
    }

    public void setDislike_num(int dislike_num) {
        this.dislike_num = dislike_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getExcellent() {
        return excellent;
    }

    public void setExcellent(int excellent) {
        this.excellent = excellent;
    }

    public int getPricecount() {
        return pricecount;
    }

    public void setPricecount(int pricecount) {
        this.pricecount = pricecount;
    }

    public int getFamous() {
        return famous;
    }

    public void setFamous(int famous) {
        this.famous = famous;
    }

    public String getPscore() {
        return Pscore;
    }

    public void setPscore(String pscore) {
        Pscore = pscore;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getIs_issue() {
        return is_issue;
    }

    public void setIs_issue(String is_issue) {
        this.is_issue = is_issue;
    }

    public int getSelf_task() {
        return self_task;
    }

    public void setSelf_task(int self_task) {
        this.self_task = self_task;
    }

    public UserInfo getUserInfo() {
        return userinfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userinfo = userInfo;
    }

    public SceneInfo getScene() {
        return scene;
    }

    public void setScene(SceneInfo scene) {
        this.scene = scene;
    }

    public List<AnswerInfoEntity> getSuit_list() {
        return suit_list;
    }
}
