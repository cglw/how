package com.prettyyes.user.model.system;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.system
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SystemAlertMessage extends BaseModel{
    private String id;
    private String image_src;
    private String url_link;
    private String end_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public String getUrl_link() {
        return url_link;
    }

    public void setUrl_link(String url_link) {
        this.url_link = url_link;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
