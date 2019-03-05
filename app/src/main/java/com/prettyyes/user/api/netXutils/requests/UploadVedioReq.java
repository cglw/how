package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.model.v8.VedioEntity;

import java.io.File;
import java.util.HashMap;

/**
 * Created by chengang on 2017/12/5.
 */

public class UploadVedioReq extends BaseRequest<VedioEntity> {
    @Override
    public String setExtraUrl() {
        return "/app/user/uploadVideo";
    }

    private File binary_video;

    public UploadVedioReq setBinary_video(File binary_video) {
        this.binary_video = binary_video;
        return this;
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("binary_video", binary_video);
        file_path = binary_video.getAbsolutePath();
        return super.setParams();
    }

    public Object tag;

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
