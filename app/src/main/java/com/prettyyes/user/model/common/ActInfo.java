package com.prettyyes.user.model.common;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/2/27.
 */

public class ActInfo extends BaseModel{
    private int act_id;
    private String act_content;
    private String act_name;
    private String act_img;
    private String start_time;
    private String create_time;
    private String end_time;
    private String hash_tag;
    private String server_time;
    private ShareModel share_model;
    private List<SellerInfoEntity> seller_list;
    private ShareModel begin_share_model;

    public ShareModel getBegin_share_model() {
        return begin_share_model;
    }

    private String is_follow;
    private int remind_time;

    public int getRemind_time() {
        return remind_time;
    }

    public void setRemind_time(int remind_time) {
        this.remind_time = remind_time;
    }

    public String getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(String is_follow) {
        this.is_follow = is_follow;
    }

    public List<SellerInfoEntity> getSeller_list() {
        return seller_list;
    }

    public void setSeller_list(List<SellerInfoEntity> seller_list) {
        this.seller_list = seller_list;
    }

    public ShareModel getShare_model() {
        return share_model;
    }

    public void setShare_model(ShareModel share_model) {
        this.share_model = share_model;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public int getAct_id() {
        return act_id;
    }

    public void setAct_id(int act_id) {
        this.act_id = act_id;
    }

    public String getAct_content() {
        return act_content;
    }

    public void setAct_content(String act_content) {
        this.act_content = act_content;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_img() {
        return act_img;
    }

    public void setAct_img(String act_img) {
        this.act_img = act_img;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getServer_time() {
        return server_time;
    }

    public void setServer_time(String server_time) {
        this.server_time = server_time;
    }
}
