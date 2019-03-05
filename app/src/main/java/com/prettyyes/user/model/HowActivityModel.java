package com.prettyyes.user.model;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/11/4
 * Description: Nothing
 */
public class HowActivityModel {
    private String lastMeseage;
    private long receivetime;
    private String headimg;
    private String type;
    private String title;
    private int unread=0;

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public String getLastMeseage() {
        return lastMeseage;
    }

    public void setLastMeseage(String lastMeseage) {
        this.lastMeseage = lastMeseage;
    }

    public long getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(long receivetime) {
        this.receivetime = receivetime;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
