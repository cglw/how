package com.prettyyes.user.app.ui.appview.model;

import com.luck.picture.lib.entity.LocalMedia;

/**
 * Created by chengang on 2017/12/13.
 */

public class HowLocalMedia extends LocalMedia {
    private String remote_url = "";
    private String cover_img = "";
    private int progress;
    private int last_progress;

    public void setLast_progress(int last_progress) {
        this.last_progress = last_progress;
    }

    public int getLast_progress() {
        return last_progress;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setRemote_url(String remote_url) {
        this.remote_url = remote_url;
    }

    public String getRemote_url() {
        if (remote_url == null)
            return "";
        return remote_url;
    }

    public boolean isUploadSuccess() {
        if (remote_url == null)
            return false;
        if (remote_url.startsWith("http")) {
            return true;
        }
        return false;
    }
}
