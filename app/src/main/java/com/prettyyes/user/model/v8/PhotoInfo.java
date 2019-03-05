package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/6/25.
 */

public class PhotoInfo extends BaseModel{
    private String path;
    private String photo_id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }
}
