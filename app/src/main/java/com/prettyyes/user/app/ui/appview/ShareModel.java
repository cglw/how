package com.prettyyes.user.app.ui.appview;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/16
 * Description: Nothing
 */
public class ShareModel extends BaseModel {
    private String share_url;
    private String share_img;
    private String share_title;
    private String share_content;
    private String type;
    private String share_weibo_img;
    private String share_weibo_content;
    public String share_type;
    public String path;
    public String username;
    public String seller_id;
    public String type_id;
    public String task_id;


    public String getShare_type() {
        return share_type;
    }

    public void setShareTypeImage() {
        this.share_type = "image";
    }

    public boolean TypeIsImg() {
        return "image".equals(share_type);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_id() {
        return task_id;
    }

    private int id;

    public String getShare_weibo_img() {
        return share_weibo_img;
    }

    public void setShare_weibo_img(String share_weibo_img) {
        this.share_weibo_img = share_weibo_img;
    }

    public String getShare_weibo_content() {
        return share_weibo_content;
    }

    public void setShare_weibo_content(String share_weibo_content) {
        this.share_weibo_content = share_weibo_content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTargetUrl() {
        return share_url;
    }

    public void setTargetUrl(String targetUrl) {
        this.share_url = targetUrl;
    }

    public String getImage() {
        return share_img;
    }

    public void setImage(String image) {
        this.share_img = image;
    }

    public String getTitle() {
        return share_title;
    }

    public void setTitle(String title) {
        this.share_title = title;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }


}
