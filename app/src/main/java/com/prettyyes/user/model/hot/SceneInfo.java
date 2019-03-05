package com.prettyyes.user.model.hot;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.hot
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SceneInfo extends BaseModel{

    /**
     * hot_id : 16
     * name : 我为什么这么爱钱？
     * cover_img : null
     * like_num : 0
     */

    private int hot_id;
    private String name;
    private String cover_img;
    private int like_num;
    private String content;

    public void setHot_id(int hot_id) {
        this.hot_id = hot_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getHot_id() {
        return hot_id;
    }

    public String getName() {
        return name;
    }

    public String getCover_img() {
        return cover_img;
    }

    public int getLike_num() {
        return like_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
