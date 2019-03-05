package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/5.
 */

public class RichEditBackEvent {
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RichEditBackEvent(String desc) {
        this.desc = desc;
    }
}
