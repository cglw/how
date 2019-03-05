package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2018/1/19.
 */

public class MorePageUnreadEvent {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public MorePageUnreadEvent(int num) {
        this.num = num;
    }
}
