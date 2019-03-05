package com.prettyyes.user.model;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/11/28
 * Description: Nothing
 */
public class KollistAdv  {
    public KollistAdv(String covery_img, String link) {
        this.covery_img = covery_img;
        this.link = link;
    }

    private String covery_img;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCovery_img() {
        return covery_img;
    }

    public void setCovery_img(String covery_img) {
        this.covery_img = covery_img;
    }
}
