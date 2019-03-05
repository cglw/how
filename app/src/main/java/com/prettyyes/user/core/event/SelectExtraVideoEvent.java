package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2018/1/31.
 */

public class SelectExtraVideoEvent {
    private String video_path;
    private String video_covery;

    public SelectExtraVideoEvent(String video_path, String video_covery) {
        this.video_covery = video_covery;
        this.video_path = video_path;
    }

    public String getVideo_covery() {
        return video_covery;
    }

    public String getVideo_path() {
        return video_path;
    }
}
