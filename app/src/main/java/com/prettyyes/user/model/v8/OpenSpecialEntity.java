package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2017/7/18.
 */

public class OpenSpecialEntity {

    /**
     * reward_id : 0
     * reward_name : string
     * reward_url : string
     * reward_desc : string
     * img_url : string
     * start_time : string
     * end_time : string
     * hot_update : string
     * hot_update_url : string
     */

    private String reward_id;
    private String reward_name;
    private String reward_url;
    private String reward_desc;
    private String img_url;
    private String start_time;
    private String end_time;
    private String hot_update;
    private String hot_update_url;

    public String getReward_id() {
        return reward_id;
    }

    public void setReward_id(String reward_id) {
        this.reward_id = reward_id;
    }

    public String getReward_name() {
        return reward_name;
    }

    public void setReward_name(String reward_name) {
        this.reward_name = reward_name;
    }

    public String getReward_url() {
        return reward_url;
    }

    public void setReward_url(String reward_url) {
        this.reward_url = reward_url;
    }

    public String getReward_desc() {
        return reward_desc;
    }

    public void setReward_desc(String reward_desc) {
        this.reward_desc = reward_desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    public String getHot_update() {
        return hot_update;
    }

    public void setHot_update(String hot_update) {
        this.hot_update = hot_update;
    }

    public String getHot_update_url() {
        return hot_update_url;
    }

    public void setHot_update_url(String hot_update_url) {
        this.hot_update_url = hot_update_url;
    }
}
