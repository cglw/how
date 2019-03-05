package com.prettyyes.user.model;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/9/19
 * Description: Nothing
 */
public class AdvModel {
    private String name;
    private int is_open;
    private String adver_url;
    private String img_url;
    private String start_time;
    private String end_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_open() {
        return is_open;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
    }

    public String getAdver_url() {
        return adver_url;
    }

    public void setAdver_url(String adver_url) {
        this.adver_url = adver_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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
}
