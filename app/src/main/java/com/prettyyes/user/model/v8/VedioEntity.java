package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/12/5.
 */

public class VedioEntity extends BaseModel {

    /**
     * video_url : string
     * video_cover_img : string
     */

    private String video_url;
    private String video_cover_img;

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_cover_img() {
        return video_cover_img;
    }

    public void setVideo_cover_img(String video_cover_img) {
        this.video_cover_img = video_cover_img;
    }
}
