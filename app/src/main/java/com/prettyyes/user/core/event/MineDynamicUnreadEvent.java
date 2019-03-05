package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/7/17.
 */

public class MineDynamicUnreadEvent {
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public MineDynamicUnreadEvent(int num) {
        this.num = num;
    }
}
