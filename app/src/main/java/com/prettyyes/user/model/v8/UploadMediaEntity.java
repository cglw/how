package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/12/6.
 */

public class UploadMediaEntity extends BaseModel {

    /**
     * img : img_url
     * video : video_url
     */

    private String type;
    private List<String> url;
    private String cover_image;

    public List<String> getUrl() {
        if (url == null)
            url = new ArrayList<>();
        return url;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImg(List<String> img) {
        setType("image");
        setCover_image("");
        getUrl().clear();
        getUrl().addAll(img);
    }

    public void addImg(String img) {
        setType("image");
        setCover_image("");
        getUrl().add(img);
    }

    public boolean isVedio() {
        if (type == null)
            return false;
        return type.equals("video") || type.equals("vedio");
    }

    public boolean isImage() {
        if (type == null)
            return false;
        return type.equals("image") || type.equals("img");
    }

    public void setVideo(String video) {
        setType("video");
        getUrl().clear();
        getUrl().add(video);


    }


}
