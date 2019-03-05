package com.prettyyes.user.app.account;

import java.io.Serializable;

/**
 * Created by chengang on 2017/5/25.
 * app 登录信息
 */

public class AppInfo implements Serializable {

    private long time_unlogin;
    private String tag;
    private int is_registered;

    public int getIs_registered() {
        return is_registered;
    }

    public void setIs_registered(int is_registered) {
        this.is_registered = is_registered;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private boolean ishaveShowUnregister;

    public boolean ishaveShowUnregister() {
        return ishaveShowUnregister;
    }

    public void setIshaveShowUnregister(boolean ishaveShowUnregister) {
        this.ishaveShowUnregister = ishaveShowUnregister;
    }

    public long getTime_unlogin() {
        return time_unlogin;
    }

    public void setTime_unlogin(long time_unlogin) {
        this.time_unlogin = time_unlogin;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "time_unlogin=" + time_unlogin +
                ", tag='" + tag + '\'' +
                ", is_registered=" + is_registered +
                ", ishaveShowUnregister=" + ishaveShowUnregister +
                '}';
    }
}
