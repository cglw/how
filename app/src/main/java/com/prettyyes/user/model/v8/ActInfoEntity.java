package com.prettyyes.user.model.v8;

import java.util.List;

/**
 * Created by chengang on 2017/7/11.
 */

public class ActInfoEntity {


    /**
     * act_id : 42
     * act_content : 123
     * act_name : 233
     * act_img : https://image.prettyyes.com/seller-laravel-9-4911-1488532648.jpeg
     * status : 1
     * create_time : 2017-03-03 17:17:50
     * start_time : 2017-03-15 19:55:56
     * end_time : 2017-03-17 01:17:35
     * hash_tag : 123
     * act_seller_id : [{"id":32446,"session_ace_txt":"331"}]
     */

    private String act_id;
    private String act_content;
    private String act_name;
    private String act_img;
    private String status;
    private String create_time;
    private String start_time;
    private String end_time;
    private String hash_tag;
    private String act_seller_id;
    List<SellerInfoEntity>seller_list;

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getAct_seller_id() {
        return act_seller_id;
    }

    public void setAct_seller_id(String act_seller_id) {
        this.act_seller_id = act_seller_id;
    }

    public List<SellerInfoEntity> getSeller_list() {
        return seller_list;
    }

    public void setSeller_list(List<SellerInfoEntity> seller_list) {
        this.seller_list = seller_list;
    }
}
